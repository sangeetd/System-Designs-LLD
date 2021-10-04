package com.sangeet.logisticsystem.project.service;

import com.sangeet.logisticsystem.project.model.*;
import com.sangeet.logisticsystem.project.repo.VehicleOrderRepo;
import com.sangeet.logisticsystem.project.repo.VehicleRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VehicleService {

    @Autowired
    private VehicleRepo vehicleRepo;

    @Autowired
    private VehicleOrderRepo vehicleOrderRepo;

    public Vehicle registerBikeVehicle(String vehicleNo, String driverName, Location currLocation) {
        Vehicle newBike = new BikeDelivery(vehicleNo, driverName, currLocation);
        this.vehicleRepo.save(newBike);
        return newBike;
    }

    public List<Order> getAllOrdersFromVehicle(String vehicleNo){
        return this.vehicleOrderRepo.getAllCurrentOrdersOfVehicle(vehicleNo);
    }

    public void orderAssigningToVehicle(Order order){
        //all available vehicles
        List<Vehicle> availableVehicle = this.vehicleRepo.allAvailableVehicle();
        //vehicle which is having capacity to take order
        Optional<Vehicle> vehicle = availableVehicle.stream()
                .filter(veh -> veh.hasCapacity())
                .findFirst();

        if(vehicle.isPresent()){
            Vehicle vehicle1 = vehicle.get();
            this.vehicleOrderRepo.save(vehicle1.getVehicleNo(), order);
            vehicle1.setCurrentOrderTakingCapacity(vehicle1.getCurrentOrderTakingCapacity() + 1);
            order.setOrderStatus(OrderStatus.IN_PROCESS);
            order.setAssignedVehicleForDelivery(vehicle1);
            if(!vehicle1.hasCapacity()){
                //vehicle filled with all the max orders it can take for delivery
                vehicle1.setVehicleStatus(VehicleStatus.ON_DELIVERY);
                //once vehicle has all the order it will on-delivery status
                //so all the order should be on out_for_delivery
                List<Order> orderOfVehicle = this.vehicleOrderRepo.getAllCurrentOrdersOfVehicle(vehicle1.getVehicleNo());
                for(Order order1: orderOfVehicle){
                    order1.setOrderStatus(OrderStatus.OUT_FOR_DELIVERY);
                }
            }
        }
    }

    public void unassigningOrderFromVehicle(Order order){

        if(order == null){
            return;
        }

        Vehicle vehicleFromThisOrder = order.getAssignedVehicleForDelivery();

        if(vehicleFromThisOrder != null){
            vehicleFromThisOrder.setCurrentOrderTakingCapacity(
                    vehicleFromThisOrder.getCurrentOrderTakingCapacity() - 1);
            if(vehicleFromThisOrder.getCurrentOrderTakingCapacity() <= 0){
                vehicleFromThisOrder.setVehicleStatus(VehicleStatus.AVAILABLE);
            }
            this.vehicleOrderRepo.delete(vehicleFromThisOrder.getVehicleNo(), order);
        }

    }

}
