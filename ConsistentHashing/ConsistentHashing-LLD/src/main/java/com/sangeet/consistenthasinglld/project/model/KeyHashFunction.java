package com.sangeet.consistenthasinglld.project.model;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class KeyHashFunction<Key> implements IHashFunction<Key> {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private final Integer MODULO;

    public KeyHashFunction(Integer modulo) {
        this.MODULO = modulo;
    }

    @Override
    public Integer generateHashIndex(Key key) {
        logger.info("Log: generating key hash index for data-key in KeyHashFunctionClas: Key = "
                + key.toString()
                + " Hash index = "
                + Math.abs(key.hashCode() % MODULO));
//        System.out.println("fun: " + key.toString() + " " + Math.abs(key.hashCode() % MODULO));
        return Math.abs(key.hashCode() % MODULO);
    }
}
