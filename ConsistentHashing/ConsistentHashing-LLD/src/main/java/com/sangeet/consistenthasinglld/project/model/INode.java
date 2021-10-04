package com.sangeet.consistenthasinglld.project.model;

import java.util.Set;

public interface INode<Key, Value> {

    void put(Key key, Value value);
    Value get(Key key);
    Value remove(Key key);
    Set<Key> keySet();
}
