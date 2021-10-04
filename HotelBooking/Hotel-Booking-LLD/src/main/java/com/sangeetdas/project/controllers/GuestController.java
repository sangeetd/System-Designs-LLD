package com.sangeetdas.project.controllers;

import com.sangeetdas.project.model.BookingDetails;
import com.sangeetdas.project.model.Hotel;
import com.sangeetdas.project.model.Room;
import com.sangeetdas.project.payment.IPaymentStrategy;
import com.sangeetdas.project.server.Server;
import com.sun.istack.internal.NotNull;

import java.util.List;

public class GuestController {

    private Server server;
    private CommonController commonController;

    public GuestController(Server server, CommonController commonController) {
        this.server = server;
        this.commonController = commonController;
    }

    public List<Hotel> searchHotels(){
        return commonController.findAllHotels();
    }

    public List<Room> searchAllAvailableRooms(@NotNull String hotelId){
        return commonController.findAllAvailableRooms(hotelId);
    }

    public BookingDetails bookARoom(@NotNull String hotelId, @NotNull List<String> roomId,
                                    @NotNull String userEmail,
                                    @NotNull String arrivalDate, @NotNull String departureDate,
                                    @NotNull IPaymentStrategy paymentStrategy){
        return this.server.getBookingsManager().bookARoom(hotelId, roomId, userEmail,
                arrivalDate, departureDate,
                paymentStrategy);
    }

    public void cancelBooking(@NotNull String userEmail, @NotNull String bookingId){
        this.server.getBookingsManager().cancelBooking(userEmail, bookingId);
    }

    public List<BookingDetails> bookingHistory(@NotNull String userEmail){
        return this.server.getBookingsManager().bookingHistory(userEmail);
    }

}
