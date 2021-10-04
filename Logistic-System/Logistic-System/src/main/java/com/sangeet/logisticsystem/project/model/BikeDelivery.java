package com.sangeet.logisticsystem.project.model;

public class BikeDelivery extends Vehicle{

    public BikeDelivery(String vehicleNo, String driverName, Location currLocation) {
        super(vehicleNo, driverName, currLocation, 2);
    }

}
