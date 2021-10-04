package com.sangeet.project.storage;

import com.sangeet.project.evictionpolicy.IEvictionPolicy;

import java.util.HashMap;
import java.util.Map;

public class HashMapBasedStorage<Key, Value> implements IStorage<Key, Value>{

    private IEvictionPolicy<Key> evictionPolicy;
    private final Map<Key, Value> mapStorage;
    private final Integer capacity;

    public HashMapBasedStorage(Integer capacity) {
        this.capacity = capacity;
        this.mapStorage = new HashMap<>(capacity);
    }

    @Override
    public boolean put(Key key, Value value){

        if(this.mapStorage.size() >= this.capacity){
            Key evictedKey = this.evictionPolicy.evictKey();
            //out of storage the key is evicted and the evicted key needs to removed out of
            //actual map storage
            this.mapStorage.remove(evictedKey);
        }
        this.mapStorage.put(key, value);
        this.evictionPolicy.updateKeyAccessed(key);
        return true;
    }

    @Override
    public Value get(Key key) throws Exception {

        if(!this.mapStorage.containsKey(key)){
            throw new Exception("Key not available: "+key);
        }
        this.evictionPolicy.updateKeyAccessed(key);
        return this.mapStorage.get(key);
    }

    @Override
    public boolean remove(Key key) throws Exception {
        if(!this.mapStorage.containsKey(key)){
            throw new Exception("Key not available: "+key);
        }
        this.mapStorage.remove(key);
        this.evictionPolicy.removeKey(key);
        return true;
    }

    @Override
    public void useEvictionPolicy(IEvictionPolicy<Key> evictionPolicy) throws Exception {
        if(this.evictionPolicy == null){
            this.evictionPolicy = evictionPolicy;
        }else {
            throw new Exception("Eviction policy already defined for cache storage");
        }
    }
}
