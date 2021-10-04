package com.sangeet.logisticsystem.project.model;

public class Vehicle {

    private final String vehicleNo;
    private String driverName;
    private Location currLocation;
    private VehicleStatus vehicleStatus;
    private Integer currentOrderTakingCapacity;
    private final Integer MAX_ORDER_TAKING_CAPACITY;
    public Vehicle(String vehicleNo, String driverName, Location currLocation, Integer MAX_ORDER_TAKING_CAPACITY) {
        this.vehicleNo = vehicleNo;
        this.driverName = driverName;
        this.currLocation = currLocation;
        this.vehicleStatus = VehicleStatus.AVAILABLE;
        this.currentOrderTakingCapacity = 0;
        this.MAX_ORDER_TAKING_CAPACITY = MAX_ORDER_TAKING_CAPACITY;
    }

    public String getVehicleNo() {
        return vehicleNo;
    }

    public String getDriverName() {
        return driverName;
    }

    public VehicleStatus getVehicleStatus() {
        return vehicleStatus;
    }

    public void setVehicleStatus(VehicleStatus vehicleStatus) {
        this.vehicleStatus = vehicleStatus;
    }

    public Integer getCurrentOrderTakingCapacity() {
        return currentOrderTakingCapacity;
    }

    public void setCurrentOrderTakingCapacity(Integer currentOrderTakingCapacity) {
        if(hasCapacity()){
            this.currentOrderTakingCapacity = currentOrderTakingCapacity;
        }
    }

    public Integer getMAX_ORDER_TAKING_CAPACITY() {
        return MAX_ORDER_TAKING_CAPACITY;
    }

    public boolean hasCapacity(){
        return getCurrentOrderTakingCapacity() < getMAX_ORDER_TAKING_CAPACITY();
    }

}
