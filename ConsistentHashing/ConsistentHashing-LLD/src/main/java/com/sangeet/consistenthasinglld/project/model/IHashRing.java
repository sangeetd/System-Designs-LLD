package com.sangeet.consistenthasinglld.project.model;

public interface IHashRing {
    
    boolean isOccupied(Integer index);
    void markOccupied(Integer index);
    void deOccupy(Integer index);
    
}
