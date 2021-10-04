package com.sangeet.consistenthasinglld.project.model;

public interface IHashFunction<Key> {

    Integer generateHashIndex(Key key);

}
