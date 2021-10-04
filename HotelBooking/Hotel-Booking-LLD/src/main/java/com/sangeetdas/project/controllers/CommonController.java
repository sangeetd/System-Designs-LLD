package com.sangeetdas.project.controllers;

import com.sangeetdas.project.model.Hotel;
import com.sangeetdas.project.model.Room;
import com.sangeetdas.project.server.Server;
import com.sun.istack.internal.NotNull;

import java.util.List;

public class CommonController {

    private Server server;

    public CommonController(Server server) {
        this.server = server;
    }

    public List<Hotel> findAllHotels(){
        return server.getHotelsManager().findAllHotels();
    }

    public List<Room> findAllAvailableRooms(@NotNull String hotelId){
        return server.getHotelsManager().findAllAvailableRooms(hotelId);
    }

}
