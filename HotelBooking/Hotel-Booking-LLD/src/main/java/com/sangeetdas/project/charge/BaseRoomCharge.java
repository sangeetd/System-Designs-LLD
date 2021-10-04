package com.sangeetdas.project.charge;

import com.sangeetdas.project.model.Room;

public class BaseRoomCharge implements IBaseCharge{

    private final double baseAmount;

    public BaseRoomCharge(double baseAmount) {
        this.baseAmount = baseAmount;
    }

    @Override
    public double getCharge() {
        return this.baseAmount;
    }
}
