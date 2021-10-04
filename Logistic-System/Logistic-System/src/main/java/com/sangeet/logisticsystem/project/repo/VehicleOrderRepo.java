package com.sangeet.logisticsystem.project.repo;

import com.sangeet.logisticsystem.project.model.Order;
import com.sangeet.logisticsystem.project.model.Vehicle;
import org.springframework.stereotype.Repository;

import java.util.*;
import java.util.stream.Collectors;

@Repository
public class VehicleOrderRepo {

    private final Map<String, List<Order>> vehicleToOrderMap;

    public VehicleOrderRepo() {
        this.vehicleToOrderMap = new HashMap<>();
    }

    public void save(String vehicleNo, Order order){
        this.vehicleToOrderMap.putIfAbsent(vehicleNo, new ArrayList<>());
        this.vehicleToOrderMap.get(vehicleNo).add(order);
    }

    public List<Order> getAllCurrentOrdersOfVehicle(String vehicleNo){
        if(vehicleToOrderMap.containsKey(vehicleNo)){
            return vehicleToOrderMap.get(vehicleNo);
        }
        return Collections.emptyList();
    }

    public void delete(String vehicleNo, Order order) {
        if(vehicleToOrderMap.containsKey(vehicleNo)){
            vehicleToOrderMap.get(vehicleNo).remove(order);
        }
    }
}
