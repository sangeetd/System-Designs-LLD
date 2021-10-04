package com.sangeetdas.project.model;

import com.sangeetdas.project.charge.IBaseCharge;
import com.sangeetdas.project.payment.IPaymentStrategy;
import com.sangeetdas.project.payment.PaymentType;

import java.util.Date;
import java.util.List;
import java.util.UUID;

public class BookingDetails {

    private String id;
    private String userEmail;
    private Hotel hotel;
    private List<Room> rooms;
    private double totalBill;
    private PaymentType paymentType;
    private String bookingDate;
    private String arrivalDate;
    private String departureDate;
    private BookingStatus bookingStatus;

    public BookingDetails(final String userEmail, final Hotel hotel, final List<Room> rooms,
                          final double totalBill,
                          final String arrivalDate, final String departureDate,
                          final PaymentType paymentType) {
        this.id = UUID.randomUUID().toString();
        this.userEmail = userEmail;
        this.hotel = hotel;
        this.rooms = rooms;
        this.totalBill = totalBill;
        this.arrivalDate = arrivalDate;
        this.departureDate = departureDate;
        this.paymentType = paymentType;
        this.bookingDate = new Date().toString();
        this.bookingStatus = BookingStatus.BOOKED;
    }

    public String getId() {
        return id;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public Hotel getHotel() {
        return hotel;
    }

    public List<Room> getRooms() {
        return rooms;
    }

    public double getTotalBill() {
        return totalBill;
    }

    public PaymentType getPaymentType() {
        return paymentType;
    }

    public String getBookingDate() {
        return bookingDate;
    }

    public String getArrivalDate() {
        return arrivalDate;
    }

    public String getDepartureDate() {
        return departureDate;
    }

    public BookingStatus getBookingStatus() {
        return bookingStatus;
    }

    public void setBookingStatus(BookingStatus bookingStatus) {
        this.bookingStatus = bookingStatus;
    }

    public String getBriefBill(){
        return new StringBuilder()
                .append("Booking id: "+this.id)
                .append("\n")
                .append("Booked on: "+this.bookingDate)
                .append("\n")
                .append("Arrival on: "+this.arrivalDate+" - Departure on: "+this.departureDate)
                .append("\n")
                .append("Booked by: "+userEmail)
                .append("\n")
                .append("Hotel name: "+hotel.getHotelName())
                .append("\n")
                .append("Total room(s) booked: "+rooms.size())
                .append("\n")
                .append("Amount: "+totalBill)
                .append(" - ")
                .append("Paid via: "+paymentType.name())
                .append("\n")
                .append("Booking status: "+bookingStatus.name())
                .toString();
    }

}
