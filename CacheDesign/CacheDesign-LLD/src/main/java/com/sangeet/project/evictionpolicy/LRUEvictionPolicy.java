package com.sangeet.project.evictionpolicy;

import com.sangeet.project.dlllogic.DoublyLinkedList;
import com.sangeet.project.dlllogic.Node;

import java.util.HashMap;
import java.util.Map;

public class LRUEvictionPolicy<Key> implements IEvictionPolicy<Key> {

    private DoublyLinkedList<Key> doublyLinkedList;
    private Map<Key, Node<Key>> map;

    public LRUEvictionPolicy() {
        this.doublyLinkedList = new DoublyLinkedList<>();
        this.map = new HashMap<>();
    }

    @Override
    public void updateKeyAccessed(Key key) {
        if (map.containsKey(key)) {
            doublyLinkedList.remove(map.get(key));
            doublyLinkedList.addAtHead(map.get(key));
        } else {
            Node<Key> newNode = new Node<>(key);
            doublyLinkedList.addAtHead(newNode);
            map.put(key, newNode);
        }
    }

    @Override
    public Key evictKey() {
        Node<Key> tail = doublyLinkedList.getTail();
        if(tail == null) {
            return null;
        }
        doublyLinkedList.remove(tail);
        map.remove(tail.getKey());
//        System.out.println("Evict:  "+tail.getKey().toString());
        return tail.getKey();
    }

    @Override
    public void removeKey(Key key) {
        if (map.containsKey(key)) {
            doublyLinkedList.remove(map.get(key));
            map.remove(key);
        }
    }
}
