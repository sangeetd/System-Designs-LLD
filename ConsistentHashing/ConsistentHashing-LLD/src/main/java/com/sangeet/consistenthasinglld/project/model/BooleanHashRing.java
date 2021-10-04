package com.sangeet.consistenthasinglld.project.model;

public class BooleanHashRing implements IHashRing{

    private final Integer HASH_RING_LIMIT;
    private boolean[] hashRing;

    public BooleanHashRing(Integer HASH_RING_LIMIT) {
        this.HASH_RING_LIMIT = HASH_RING_LIMIT;
        this.hashRing = new boolean[HASH_RING_LIMIT];
    }

    @Override
    public boolean isOccupied(Integer index) {
        if(index < 0 && index >= this.HASH_RING_LIMIT.intValue()){
            throw new RuntimeException("Exception = HashRingIndexOutOfBoundException: Index "+index+" out of range "+this.HASH_RING_LIMIT);
        }
        return this.hashRing[index];
    }

    @Override
    public void markOccupied(Integer index) {
        if(index < 0 && index >= this.HASH_RING_LIMIT.intValue()){
            throw new RuntimeException("Exception = HashRingIndexOutOfBoundException: Index "+index+" out of range "+this.HASH_RING_LIMIT);
        }
        this.hashRing[index] = true;
    }

    @Override
    public void deOccupy(Integer index) {
        if(index < 0 && index >= this.HASH_RING_LIMIT.intValue()){
            throw new RuntimeException("Exception = HashRingIndexOutOfBoundException: Index "+index+" out of range "+this.HASH_RING_LIMIT);
        }
        this.hashRing[index] = false;
    }

    public boolean[] getHashRing(){
        return this.hashRing;
    }

}
