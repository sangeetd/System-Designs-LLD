package com.sangeet.project;

import com.sangeet.project.evictionpolicy.IEvictionPolicy;
import com.sangeet.project.storage.IStorage;

public class CacheImpl<Key, Value> {

    private final IStorage<Key, Value> storage;

    public CacheImpl(IStorage<Key, Value> storage, IEvictionPolicy<Key> evictionPolicy) {
        this.storage = storage;
        try {
            this.storage.useEvictionPolicy(evictionPolicy);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean put(Key key, Value value){
        try {
            return this.storage.put(key, value);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public Value get(Key key){
        try {
            return this.storage.get(key);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public void remove(Key key){
        try {
            this.storage.remove(key);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
