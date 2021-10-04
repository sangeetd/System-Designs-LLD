package com.sangeetdas.project.server;

public class Server {

    private static Server instance = null;
    private HotelsManager hotelsManager;
    private BookingsManager bookingsManager;
    private Server(){
    }

    public static Server getInstance(){

        if(instance == null){
            instance = new Server();
        }

        return instance;

    }

    public void registerHotelsManager(HotelsManager hotelsManager){
        this.hotelsManager = hotelsManager;
    }

    public void registerBookingsManager(BookingsManager bookingsManager){
        this.bookingsManager = bookingsManager;
    }

    public HotelsManager getHotelsManager() {
        return hotelsManager;
    }

    public BookingsManager getBookingsManager() {
        return bookingsManager;
    }
}
