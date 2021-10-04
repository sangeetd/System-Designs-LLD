package com.sangeetdas.project.model;


import com.sangeetdas.project.charge.IBaseCharge;

import java.util.UUID;

public class Room {
    private String id;
    private RoomType roomType;
    private RoomStatus roomStatus;

    public Room(RoomType roomType, RoomStatus roomStatus) {
        this.id = UUID.randomUUID().toString();
        this.roomType = roomType;
        this.roomStatus = roomStatus;
    }

    public String getId() {
        return id;
    }

    public RoomType getRoomType() {
        return roomType;
    }

    public RoomStatus getRoomStatus() {
        return roomStatus;
    }

    public void setRoomStatus(RoomStatus roomStatus) {
        this.roomStatus = roomStatus;
    }

    @Override
    public String toString() {
        return "Room{" +
                "id='" + id + '\'' +
                ", roomType=" + roomType.name() +
                ", roomStatus=" + roomStatus.name() +
                '}';
    }
}
