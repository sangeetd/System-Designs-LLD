package com.sangeetdas.project;

import com.sangeetdas.project.controllers.AdminController;
import com.sangeetdas.project.controllers.CommonController;
import com.sangeetdas.project.controllers.GuestController;
import com.sangeetdas.project.model.*;
import com.sangeetdas.project.payment.CashPayment;
import com.sangeetdas.project.payment.UPIPayment;
import com.sangeetdas.project.server.BookingsManager;
import com.sangeetdas.project.server.HotelsManager;
import com.sangeetdas.project.server.Server;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

public class BaseTest {

    @Test
    public void test(){


        //server
        Server server = Server.getInstance();
        System.out.println("### Creating managers");
        HotelsManager hotelsManager = new HotelsManager();
        BookingsManager bookingsManager = new BookingsManager(hotelsManager);
        System.out.println("### Registering managers");
        server.registerHotelsManager(hotelsManager);
        server.registerBookingsManager(bookingsManager);
        System.out.println("### Creating controllers");
        //controller
        CommonController commonController = new CommonController(server);
        AdminController adminController = new AdminController(server);
        GuestController guestController = new GuestController(server, commonController);
        System.out.println("### Creating template addresses");
        //few Address
        Address lajpatNagar =  new Address("Lajpat Nagar", "Delhi", "Delhi", "India", "64");
        Address dwarka =  new Address("Dwarka aerocity", "Delhi", "Delhi", "India", "72");
        Address noida =  new Address("Greater Noida", "UP", "UP", "India", "121");
        System.out.println("### Creating hotel DB");
        //backend
        adminController.createHotel("Park Inn", lajpatNagar);
        adminController.createHotel("Lion Inn", dwarka);
        adminController.createHotel("JW Marriot", noida);
        System.out.println("### Adding rooms to hotels");
        List<Hotel> allHotels = commonController.findAllHotels();
        //hotel 1
        adminController.addRoomToHotel(allHotels.get(0).getId(), new Room(RoomType.DELUX, RoomStatus.AVAILABLE));
        adminController.addRoomToHotel(allHotels.get(0).getId(), new Room(RoomType.PLATINUM, RoomStatus.AVAILABLE));
        adminController.addRoomToHotel(allHotels.get(0).getId(), new Room(RoomType.GOLD, RoomStatus.AVAILABLE));
        //hotel 2
        adminController.addRoomToHotel(allHotels.get(1).getId(), new Room(RoomType.DELUX, RoomStatus.AVAILABLE));
        adminController.addRoomToHotel(allHotels.get(1).getId(), new Room(RoomType.PLATINUM, RoomStatus.AVAILABLE));
        adminController.addRoomToHotel(allHotels.get(1).getId(), new Room(RoomType.GOLD, RoomStatus.AVAILABLE));
        //hotel 3
        adminController.addRoomToHotel(allHotels.get(2).getId(), new Room(RoomType.DELUX, RoomStatus.AVAILABLE));
        adminController.addRoomToHotel(allHotels.get(2).getId(), new Room(RoomType.PLATINUM, RoomStatus.AVAILABLE));
        adminController.addRoomToHotel(allHotels.get(2).getId(), new Room(RoomType.GOLD, RoomStatus.AVAILABLE));
        System.out.println("### Guest search for all hotels");
        //guest search for all hotels
        List<Hotel> searchedHotels = guestController.searchHotels();
        searchedHotels.stream().forEach(hotel -> System.out.println(hotel));
        System.out.println("### Guest search for available rooms in a particular hotel (id)");
        //guest search for avialable rooms in particular hotel
        List<Room> availableRooms = guestController.searchAllAvailableRooms(searchedHotels.get(0).getId());
        availableRooms.stream().forEach(room -> System.out.println(room));
        System.out.println("### Guest books a room from the hotel");
        //guest book a room
        BookingDetails bookingDetails = guestController.bookARoom(searchedHotels.get(0).getId(),
                Arrays.asList(availableRooms.get(0).getId()),
                "sangeet@abc.com",
                "2021-01-30",
                "2021-02-05",
                new CashPayment());

        System.out.println("### Generating and showing bookings details");
        //checking booking details
        System.out.println(bookingDetails.getBriefBill());
        System.out.println("### Again checking for the available rooms in the same hotel user has booked a room");
        //guest search for avialable rooms in particular hotel
        availableRooms = guestController.searchAllAvailableRooms(searchedHotels.get(0).getId());
        availableRooms.stream().forEach(room -> System.out.println(room));
        System.out.println("### Cancelling the previously booked room");
        //booking cancel
        guestController.cancelBooking("sangeet@abc.com",
                bookingDetails.getId());
        System.out.println("### Again checking the available rooms after cancelling the room user has previously booked");
        //guest search for avialable rooms in particular hotel
        availableRooms = guestController.searchAllAvailableRooms(searchedHotels.get(0).getId());
        availableRooms.stream().forEach(room -> System.out.println(room));
        System.out.println("### Again booking 2 rooms from different hotel");
        availableRooms = guestController.searchAllAvailableRooms(searchedHotels.get(2).getId());
        availableRooms.stream().forEach(room -> System.out.println(room));
        bookingDetails = guestController.bookARoom(searchedHotels.get(2).getId(),
                Arrays.asList(availableRooms.get(0).getId(), availableRooms.get(2).getId()),
                "sangeet@abc.com",
                "2021-02-05",
                "2021-02-15",
                new UPIPayment("sangeet@newupi", "1111"));
        System.out.println("### Generating and showing bookings details");
        //checking booking details
        System.out.println(bookingDetails.getBriefBill());
        System.out.println("### Again checking the available rooms after booking 2 new rooms with different hotel");
        //guest search for avialable rooms in particular hotel
        availableRooms = guestController.searchAllAvailableRooms(searchedHotels.get(2).getId());
        availableRooms.stream().forEach(room -> System.out.println(room));
        System.out.println("### Checking guest booking history");
        //guest booking history
        List<BookingDetails> bookingHistory = guestController.bookingHistory("sangeet@abc.com");
        bookingHistory.stream().forEach(history -> System.out.println(history.getBriefBill()));
    }


}
