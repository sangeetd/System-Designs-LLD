package com.sangeetdas.project.model;

import com.sangeetdas.project.charge.BaseRoomCharge;
import com.sangeetdas.project.charge.IBaseCharge;

public enum RoomType {
    DELUX(new BaseRoomCharge(10000.0)),
    PLATINUM(new BaseRoomCharge(5000.0)),
    GOLD(new BaseRoomCharge(2500.0));

    private IBaseCharge defaultBaseCharge;

    RoomType(IBaseCharge defaultBaseCharge){
        this.defaultBaseCharge = defaultBaseCharge;
    }

    public IBaseCharge getDefaultBaseCharge(){
        return this.defaultBaseCharge;
    }

}
