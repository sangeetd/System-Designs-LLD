package com.sangeet.project.storage;

import com.sangeet.project.evictionpolicy.IEvictionPolicy;

public interface IStorage<Key, Value> {

    boolean put(Key key, Value value);
    Value get(Key key) throws Exception;
    boolean remove(Key key) throws Exception;
    void useEvictionPolicy(IEvictionPolicy<Key> evictionPolicy) throws Exception;

}
