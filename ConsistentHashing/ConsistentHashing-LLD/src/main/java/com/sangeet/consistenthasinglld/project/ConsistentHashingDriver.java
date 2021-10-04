package com.sangeet.consistenthasinglld.project;

import com.sangeet.consistenthasinglld.project.model.*;

import java.util.*;
import java.util.stream.Collectors;

public class ConsistentHashingDriver<Key, Value> {

    //Defaults
    private Integer DATA_REDUNDENCY_FACTOR = 1;
    private Integer NODE_REPLICATION_FACTOR = 1;

    private final IHashFunction<Key> nodeDistributionHashFunction;
    private final IHashFunction<Key> keyHashFunction;
    private final IHashRing hashRing;
    private final List<INode<Key, Value>> nodes;
    private final Map<Integer, INode> replicatedNodeIndexes;
    private final Random random;

    public ConsistentHashingDriver(Integer nodeReplicationFactor,
                                   Integer dataRedundencyFactor,
                                   IHashFunction nodeDistributionHashFunction,
                                   IHashFunction keyHashFunction,
                                   IHashRing hashRing,
                                   List<INode<Key, Value>> nodes) {
        this.NODE_REPLICATION_FACTOR = nodeReplicationFactor;
        this.DATA_REDUNDENCY_FACTOR = dataRedundencyFactor;
        this.nodeDistributionHashFunction = nodeDistributionHashFunction;
        this.keyHashFunction = keyHashFunction;
        this.hashRing = hashRing;
        this.nodes = nodes.stream().collect(Collectors.toCollection(ArrayList::new));
        this.replicatedNodeIndexes = new HashMap<>();
        this.random = new Random();
        for (INode inode : this.nodes) {
            putNodeInHashRing(inode);
        }
    }

    public ConsistentHashingDriver(IHashFunction nodeDistributionHashFunction,
                                   IHashFunction keyHashFunction,
                                   IHashRing hashRing,
                                   List<INode<Key, Value>> nodes) {
        this.nodeDistributionHashFunction = nodeDistributionHashFunction;
        this.keyHashFunction = keyHashFunction;
        this.hashRing = hashRing;
        this.nodes = nodes.stream().collect(Collectors.toCollection(ArrayList::new));
        this.replicatedNodeIndexes = new HashMap<>();
        this.random = new Random();
        for (INode inode : this.nodes) {
            putNodeInHashRing(inode);
        }
    }

    public void addNode(INode inode) {
        this.nodes.add(inode);
        putNodeInHashRing(inode);

        //after adding one more node to hash ring
        //all keys in previous set of nodes need to be rehashed to evenly distribute
        //among the new set of nodes
        reHashDataOnNodeAddition();
    }

    public void removeNode(INode<Key, Value> node) {

        List<Integer> replicatedIndex = indexesOfReplicatedNode(node);

        if (replicatedIndex.isEmpty()) {
            throw new RuntimeException("Exception = NodeNotFoundException: Provide node is not found in the system");
        }

        for (int i : replicatedIndex) {
            this.hashRing.deOccupy(i);
            this.replicatedNodeIndexes.remove(i);
        }

        reHashDataOnNodeRemoval(node);
        this.nodes.remove(node);
    }

    public void addData(Key key, Value value) {
        Integer dataIndex = this.keyHashFunction.generateHashIndex(key);
        List<Integer> possibleIndexesForData = possibleIndexesForDataKey(dataIndex);
        for (int nodeIndex : possibleIndexesForData) {
            if (this.hashRing.isOccupied(nodeIndex)) {
                this.replicatedNodeIndexes.get(nodeIndex).put(key, value);
            }
        }
    }

    public Value getData(Key key) {
        Integer dataIndex = this.keyHashFunction.generateHashIndex(key);
        List<Integer> possibleIndexesForData = possibleIndexesForDataKey(dataIndex);
        if (possibleIndexesForData == null || possibleIndexesForData.isEmpty()) {
            throw new RuntimeException("Exception = NodesNotFoundException: Not a single node found");
        }
        Value found = null;
        for (int nodeIndex : possibleIndexesForData) {
            if (this.hashRing.isOccupied(nodeIndex)) {
                found = (Value) this.replicatedNodeIndexes.get(nodeIndex).get(key);
            }
        }

        if (found == null) {
            throw new RuntimeException("Exception = KeyValueDataNotFoundException: Key is not found " + key.toString());
        }
        return found;
    }

    public void removeData(Key key) {
        Integer dataIndex = this.keyHashFunction.generateHashIndex(key);
        List<Integer> possibleIndexesForData = possibleIndexesForDataKey(dataIndex);
        if (possibleIndexesForData == null || possibleIndexesForData.isEmpty()) {
            throw new RuntimeException("Exception = NodesNotFoundException: Not a single node found");
        }
        for (int nodeIndex : possibleIndexesForData) {
            if (this.hashRing.isOccupied(nodeIndex)) {
                this.replicatedNodeIndexes.get(nodeIndex).remove(key);
            }
        }
    }

    public void nodesOccupancyRecords() {

        this.replicatedNodeIndexes.entrySet()
                .stream()
                .sorted((a, b) ->
                        ((Node) a.getValue()).getName()
                                .compareTo(((Node) b.getValue()).getName())
                )
                .forEach(e -> System.out.println(((Node) e.getValue()).getName() + " : " + e.getKey()));

        System.out.println();
        this.replicatedNodeIndexes.entrySet()
                .stream()
                .sorted((a, b) ->
                        a.getKey().compareTo(b.getKey())
                )
                .forEach(e -> System.out.println(((Node) e.getValue()).getName() + " : " + e.getKey()));

    }

    public void dataAcrossAllNodes() {

        for (INode iNode : this.nodes) {
            Node node = (Node) iNode;
            System.out.println(node.getName() + " : Data : \n" + node.getData());
        }

    }

    private void putNodeInHashRing(INode<Key, Value> inode) {

        int i = 0;
        while (i < NODE_REPLICATION_FACTOR) {
            Key nodeKey = (Key) (((Node) inode).getId() + "-" + (random.nextInt(50)));
            Integer index = this.nodeDistributionHashFunction.generateHashIndex(nodeKey);
            if (!this.hashRing.isOccupied(index)) {
                this.hashRing.markOccupied(index);
                this.replicatedNodeIndexes.put(index, inode);
                i++;
            }
        }
    }

    private List<Integer> indexesOfReplicatedNode(INode node) {
        List<Integer> indexes = new ArrayList<>();
        for (Map.Entry<Integer, INode> e : this.replicatedNodeIndexes.entrySet()) {
            if (e.getValue() == node) {
                indexes.add(e.getKey());
            }
        }
        return indexes;
    }

    private List<Integer> indexesOfReplicatedNode() {
        List<Integer> indexes = new ArrayList<>();
        for (Map.Entry<Integer, INode> e : this.replicatedNodeIndexes.entrySet()) {
            indexes.add(e.getKey());
        }
        return indexes;
    }

    private List<Integer> possibleIndexesForDataKey(int clockwiseFromPoint) {
        //all the indexes that nodes have occupied
        //below should be mainted/updated by some GOSSIP-PROTOCOL type algo
        List<Integer> allIndexes = indexesOfReplicatedNode();
        Collections.sort(allIndexes);

        //find all the indexes greater than equal to dataIndex upto required DATA_REDUNDENCY_FACTOR
        int firstDataPoint = findFirstDataPoint(allIndexes,
                0,
                allIndexes.size() - 1,
                clockwiseFromPoint);
//        System.out.println("BS: "+firstDataPoint);
        //Create circle
        allIndexes.addAll(allIndexes);
        List<Integer> requiredNodesIndex = allIndexes.subList(firstDataPoint,
                firstDataPoint + DATA_REDUNDENCY_FACTOR);
        allIndexes = null;
        return requiredNodesIndex;
    }

    private int findFirstDataPoint(List<Integer> arr,
                                   int end,
                                   int start,
                                   int find) {

        while (end >= start) {

            int mid = start + (end - start) / 2;
            if ((mid == arr.size() - 1 || find < arr.get(mid + 1))
                    && arr.get(mid) == find) {
                return mid;
            } else if (find < arr.get(mid)) {
                end = mid - 1;
            } else {
                start = mid + 1;
            }
        }
        return start;
    }

    private void reHashDataOnNodeRemoval(INode inode) {
        Node node = (Node) inode;
        Map<Key, Value> data = new HashMap<>(node.getData());
        node.getData().clear();
        for (Map.Entry<Key, Value> e : data.entrySet()) {
            addData(e.getKey(), e.getValue());
        }
    }

    private void reHashDataOnNodeAddition() {

        Map<Key, Value> allKeyValue = new HashMap<>();
        for (INode inode : this.nodes) {
            Node node = (Node) inode;
            allKeyValue.putAll(node.getData());
            node.getData().clear();
        }

        for (Map.Entry<Key, Value> e : allKeyValue.entrySet()) {
            addData(e.getKey(), e.getValue());
        }

        allKeyValue.clear();

    }

}
