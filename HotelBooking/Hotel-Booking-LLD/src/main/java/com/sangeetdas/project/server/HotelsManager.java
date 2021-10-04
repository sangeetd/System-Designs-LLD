package com.sangeetdas.project.server;

import com.sangeetdas.project.model.BookingDetails;
import com.sangeetdas.project.model.Hotel;
import com.sangeetdas.project.model.Room;
import com.sangeetdas.project.model.RoomStatus;
import com.sangeetdas.project.payment.IPaymentStrategy;
import com.sun.istack.internal.NotNull;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class HotelsManager {

    private final Map<String, Hotel> hotelDB;

    public HotelsManager() {
        this.hotelDB = new HashMap<>();
    }

    public void addNewHotel(@NotNull Hotel hotel){
        if(hotelDB.containsKey(hotel.getId())){
            throw new RuntimeException("Hotel data already exists");
        }

        hotelDB.put(hotel.getId(), hotel);
    }

    public void addNewRoom(@NotNull String hotelId, @NotNull Room room){
        if(!hotelDB.containsKey(hotelId)){
            throw new RuntimeException("Hotel you requested doesn't exists");
        }

        for(Room r: hotelDB.get(hotelId).getRooms()){
            if(r.getId() == room.getId()){
                throw new RuntimeException("Room already exists");
            }
        }

        hotelDB.get(hotelId).getRooms().add(room);

    }

    public Hotel findAHotel(@NotNull String hotelId){
        if(!hotelDB.containsKey(hotelId)){
            throw new RuntimeException("Hotel you requested doesn't exists");
        }

        return hotelDB.get(hotelId);

    }

    public List<Hotel> findAllHotels(){
        if(hotelDB.size() == 0){
            throw new RuntimeException("No hotels available at the moment");
        }

        return hotelDB.values().stream().collect(Collectors.toList());

    }

    public List<Room> findAllAvailableRooms(@NotNull String hotelId){
        if(!hotelDB.containsKey(hotelId)){
            throw new RuntimeException("Hotel not found");
        }

        return hotelDB.get(hotelId).getRooms().stream()
                .filter(room -> room.getRoomStatus() == RoomStatus.AVAILABLE)
                .collect(Collectors.toList());
    }

}
