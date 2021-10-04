package com.sangeet.consistenthasinglld.project.model;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Node<Key, Value> implements INode<Key, Value>{

    private String id;
    private String name;
    private final Map<Key, Value> data;

    public Node(String name) {
        this.name = name;
        this.id = UUID.randomUUID().toString();
        this.data = new HashMap<>();
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    @Override
    public void put(Key key, Value value) {
        this.data.put(key, value);
    }

    @Override
    public Value get(Key key) {
        return this.data.get(key);
    }

    @Override
    public Value remove(Key key) {
        return this.data.remove(key);
    }

    @Override
    public Set<Key> keySet() {
        return this.data.keySet();
    }

    public Map<Key, Value> getData(){
        return this.data;
    }

}
