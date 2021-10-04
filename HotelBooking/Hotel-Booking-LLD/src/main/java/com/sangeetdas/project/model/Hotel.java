package com.sangeetdas.project.model;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Hotel {

    private String id;
    private String hotelName;
    private Address address;
    private List<Room> rooms;

    public Hotel(String hotelName, Address address) {
        this.id = UUID.randomUUID().toString();
        this.hotelName = hotelName;
        this.address = address;
        this.rooms = new ArrayList<>();
    }

    public String getId() {
        return id;
    }

    public String getHotelName() {
        return hotelName;
    }

    public Address getAddress() {
        return address;
    }

    public List<Room> getRooms() {
        return rooms;
    }

    @Override
    public String toString() {
        return "Hotel{" +
                "id='" + id + '\'' +
                ", hotelName='" + hotelName + '\'' +
                ", Rooms='" + rooms.size() + '\'' +
                '}';
    }
}
