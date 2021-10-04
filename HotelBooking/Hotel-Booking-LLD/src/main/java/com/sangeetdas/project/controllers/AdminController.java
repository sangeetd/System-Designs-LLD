package com.sangeetdas.project.controllers;

import com.sangeetdas.project.model.Address;
import com.sangeetdas.project.model.Hotel;
import com.sangeetdas.project.model.Room;
import com.sangeetdas.project.server.Server;
import com.sun.istack.internal.NotNull;

public class AdminController {

    private Server server;

    public AdminController(Server server) {
        this.server = server;
    }

    public void createHotel(@NotNull String hotelName, @NotNull Address address){
        this.server.getHotelsManager().addNewHotel(new Hotel(hotelName,
                address));
    }

    public void addRoomToHotel(@NotNull String hotelId, @NotNull Room room){
        this.server.getHotelsManager().addNewRoom(hotelId, room);
    }

}
