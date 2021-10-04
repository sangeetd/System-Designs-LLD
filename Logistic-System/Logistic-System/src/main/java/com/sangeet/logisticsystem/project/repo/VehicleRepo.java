package com.sangeet.logisticsystem.project.repo;

import com.sangeet.logisticsystem.project.model.Vehicle;
import com.sangeet.logisticsystem.project.model.VehicleStatus;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class VehicleRepo {

    private final List<Vehicle> vehicleList;

    public VehicleRepo() {
        this.vehicleList = new ArrayList<>();
    }

    public void save(Vehicle newBike) {
        this.vehicleList.add(newBike);
    }

    public List<Vehicle> allAvailableVehicle(){
        //query
        return this.vehicleList.stream()
                .filter(vehicle -> vehicle.getVehicleStatus() == VehicleStatus.AVAILABLE)
                .collect(Collectors.toList());
    }
}
