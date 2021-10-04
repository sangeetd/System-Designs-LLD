package com.sangeet.logisticsystem.project.controller;

import com.sangeet.logisticsystem.project.model.Location;
import com.sangeet.logisticsystem.project.model.Order;
import com.sangeet.logisticsystem.project.model.Vehicle;
import com.sangeet.logisticsystem.project.service.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class VehicleDeliveryController {

    @Autowired
    private VehicleService vehicleService;

    public Vehicle registerBikeVehicle(String vehicleNo, String driverName, Location currLocation){
        return this.vehicleService.registerBikeVehicle(vehicleNo, driverName, currLocation);
    }

    public List<Order> allCurrentOrdersWithVehicle(String vehicleNo){
        return this.vehicleService.getAllOrdersFromVehicle(vehicleNo);
    }

}
