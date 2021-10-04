package com.sangeet.consistenthasinglld.project.model;

import java.util.Random;

public class NodeDistributionHashFunction<Key> implements IHashFunction<Key>{

    private final Integer MODULO;
    private final Random random;
    private Integer LOW = 10;
    private Integer HIGH = 1001;

    public NodeDistributionHashFunction(Integer MODULO) {
        this.MODULO = MODULO;
        this.random = new Random();
    }

    @Override
    public Integer generateHashIndex(Key key) {
        int code = key.hashCode();
        code += Math.min(code - (LOW + random.nextInt(HIGH - LOW)),
                code + (LOW + random.nextInt(HIGH - LOW)));
//        System.out.println("fun: "+key.toString()+" "+Math.abs(code % MODULO));
        return Math.abs(code % MODULO);
    }
}
