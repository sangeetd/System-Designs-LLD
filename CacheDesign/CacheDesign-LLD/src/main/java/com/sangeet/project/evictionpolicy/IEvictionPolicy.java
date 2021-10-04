package com.sangeet.project.evictionpolicy;

public interface IEvictionPolicy<Key> {

    void updateKeyAccessed(Key key);
    Key evictKey();
    void removeKey(Key key);

}
