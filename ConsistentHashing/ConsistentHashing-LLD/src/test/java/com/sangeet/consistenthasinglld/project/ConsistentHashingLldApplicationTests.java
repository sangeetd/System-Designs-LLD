package com.sangeet.consistenthasinglld.project;

import com.sangeet.consistenthasinglld.project.model.*;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ConsistentHashingLldApplicationTests {

    @Test
    void contextLoads() {
    }

    @Test
    public void test() {

        int hash_ring_limit = 256;

        IHashRing hashRing = new BooleanHashRing(hash_ring_limit);
        IHashFunction<String> nodeDistributionHashFunction = new NodeDistributionHashFunction<>(hash_ring_limit);
        IHashFunction<String> keyHashFunction = new KeyHashFunction<>(hash_ring_limit);

        INode<String, String> node1 = new Node("Node1");
        INode<String, String> node2 = new Node("Node2");
        INode<String, String> node3 = new Node("Node3");
        INode<String, String> node4 = new Node("Node4");
        INode<String, String> node5 = new Node("Node5");

        String dataKey1 = generateDataKey();
        String dataKey2 = generateDataKey();
        String dataKey3 = generateDataKey();
        String dataKey4 = generateDataKey();

        String value = null;
        Exception exception = null;

//		System.out.println("## Consistent hashing driver");
//		ConsistentHashingDriver<String, String> driver = new ConsistentHashingDriver<>(hashFunction,
//				hashRing,
//				Arrays.asList(node1, node2, node3));

        System.out.println("## Consistent hashing driver object with custom node replication factor");
        ConsistentHashingDriver<String, String> driver = new ConsistentHashingDriver<>(4,
                2,
                nodeDistributionHashFunction,
                keyHashFunction,
                hashRing,
                Arrays.asList(node1, node2, node3));
        System.out.println();

        System.out.println("## Occupancy records");
        driver.nodesOccupancyRecords();
        System.out.println();

        System.out.println("## Removing node 2");
        driver.removeNode(node2);
        System.out.println();

        System.out.println("## Occupancy records");
        driver.nodesOccupancyRecords();
        System.out.println();

        System.out.println("## Adding node 2 again");
        driver.addNode(node2);
        System.out.println();

        System.out.println("## Occupancy records");
        driver.nodesOccupancyRecords();
        System.out.println();

        System.out.println("## Adding datas");
        driver.addData(dataKey1, "Data-Value-1");
        driver.addData(dataKey2, "Data-Value-2");
        driver.addData(dataKey3, "Data-Value-3");
        driver.addData(dataKey4, "Data-Value-4");
        System.out.println();

        System.out.println("## Viewing data across all nodes");
        driver.dataAcrossAllNodes();
        System.out.println();

        System.out.println("## Removing node 3");
        driver.removeNode(node3);
        System.out.println();

        System.out.println("## Viewing data across all nodes (Node 3 data should rehashed among left over nodes)");
        driver.dataAcrossAllNodes();
        System.out.println();

        System.out.println("## Adding node 3 again");
        driver.addNode(node3);
        System.out.println();

        System.out.println("## Occupancy records");
        driver.nodesOccupancyRecords();
        System.out.println();

        System.out.println("## Viewing data across all nodes Node 3 again added to hash ring");
        driver.dataAcrossAllNodes();
        System.out.println();

        System.out.println("## Get ");
        value = driver.getData(dataKey1);
        System.out.println("Value fetched: " + value);
        assertEquals("Data-Value-1", value);
        value = driver.getData(dataKey3);
        System.out.println("Value fetched: " + value);
        assertEquals("Data-Value-3", value);
        System.out.println();

        System.out.println("## Adding 2 new nodes");
        driver.addNode(node4);
        driver.addNode(node5);
        System.out.println();

        System.out.println("## Occupancy records");
        driver.nodesOccupancyRecords();
        System.out.println();

        System.out.println("## Viewing data across all nodes after adding 2 new nodes in hash ring");
        driver.dataAcrossAllNodes();
        System.out.println();

        System.out.println("## Get ");
        value = driver.getData(dataKey1);
        System.out.println("Value fetched: " + value);
        assertEquals("Data-Value-1", value);
        value = driver.getData(dataKey3);
        System.out.println("Value fetched: " + value);
        assertEquals("Data-Value-3", value);
        System.out.println();

        System.out.println("## Remove data ");
        driver.removeData(dataKey1);
        System.out.println();

        System.out.println("## Viewing data across all nodes after removing Data1");
        driver.dataAcrossAllNodes();
        System.out.println();

        System.out.println("## Get ");
        exception = assertThrows(RuntimeException.class, () -> driver.getData(dataKey1));
        assertTrue(exception.getMessage().contains("Exception = KeyValueDataNotFoundException"));
        System.out.println();

    }

    private String generateDataKey(){
        return "Data-"+(UUID.randomUUID().toString().substring(0, 3)) + "-" + (UUID.randomUUID().toString().substring(0, 5));
    }

}
