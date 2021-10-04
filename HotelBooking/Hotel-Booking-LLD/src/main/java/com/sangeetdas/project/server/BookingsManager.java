package com.sangeetdas.project.server;

import com.sangeetdas.project.model.*;
import com.sangeetdas.project.payment.IPaymentStrategy;
import com.sun.istack.internal.NotNull;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BookingsManager {

    private HotelsManager hotelsManager;
    private final Map<String, List<BookingDetails>> bookingDB;

    public BookingsManager(HotelsManager hotelsManager) {
        this.hotelsManager = hotelsManager;
        this.bookingDB = new HashMap<>();
    }

    public BookingDetails bookARoom(@NotNull String hotelId, @NotNull List<String> roomIds,
                                    @NotNull String userEmail,
                                    @NotNull String arrivalDate, @NotNull String departuredate,
                                    @NotNull IPaymentStrategy paymentStrategy){

        //check is there is any hotel with given hotel id
        Hotel hotel = hotelsManager.findAHotel(hotelId);
        if(hotel == null){
            throw new RuntimeException("Hotel not found");
        }

        List<Room> allRooms = new ArrayList<>();
        //get all the room(s) req by user
        for(String rId: roomIds){
            Room room = hotel.getRooms().stream()
                    //filter those room(s) by id and they should be available
                    .filter(r -> r.getId().equals(rId) && r.getRoomStatus() == RoomStatus.AVAILABLE)
                    .findFirst().get();
            allRooms.add(room);
        }

        //if no rooms found OR some rooms are not avilable as req by user
        if(allRooms.isEmpty() || allRooms.size() != roomIds.size()){
            throw new RuntimeException("Room(s) not found or may be sold out");
        }

        //if all rooms available that users has req. get total bill
        double totalBill = 0;
        for(Room r: allRooms){
            totalBill += r.getRoomType().getDefaultBaseCharge().getCharge();
        }

        //make payment for all the room(s)
        paymentStrategy.doPayment(totalBill);

        BookingDetails bookingDetails = null;
        if(paymentStrategy.isSuccessfull()){
            //confirm all the room
            for(Room r: allRooms){
                r.setRoomStatus(RoomStatus.OCCUPIED);
            }

            bookingDetails = new BookingDetails(userEmail,
                    hotel, allRooms,
                    totalBill,
                    arrivalDate, departuredate,
                    paymentStrategy.getPaymentType());
            bookingDB.putIfAbsent(userEmail, new ArrayList<>());
            bookingDB.get(userEmail).add(bookingDetails);
        }else {
            throw new RuntimeException("Payment failed");
        }
        return bookingDetails;
    }

    public void cancelBooking(@NotNull final String userEmail, @NotNull final String bookingId){
        if(!bookingDB.containsKey(userEmail)){
            throw new RuntimeException("User not found");
        }

        //find if the bookingId is there in the userEmail
        BookingDetails bookingDetails = bookingDB.get(userEmail).stream()
                .filter( booking -> booking.getId().equals(bookingId))
                .findFirst().get();
        if(bookingDetails != null){
            //before cancelling the booking release all the booked rooms for further selling
            List<Room> previouslyBookedRooms = bookingDetails.getRooms();
            for(Room r: previouslyBookedRooms){
                r.setRoomStatus(RoomStatus.AVAILABLE);
            }
            bookingDetails.setBookingStatus(BookingStatus.CANCELED);
            //in real world no data is actually removed/deleted, just flagged out
            //bookingDB.get(userEmail).remove(bookingDetails); //.remove() not req
            System.out.println("Booking cancelled");
        }else {
            throw new RuntimeException("Booking Id is not found with the user");
        }
    }

    public List<BookingDetails> bookingHistory(@NotNull final String userEmail){
        if(!bookingDB.containsKey(userEmail)){
            throw new RuntimeException("User not found");
        }

        return bookingDB.get(userEmail);

    }

}
