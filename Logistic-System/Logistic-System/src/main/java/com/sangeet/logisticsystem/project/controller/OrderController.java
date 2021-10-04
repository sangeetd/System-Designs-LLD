package com.sangeet.logisticsystem.project.controller;

import com.sangeet.logisticsystem.project.model.Location;
import com.sangeet.logisticsystem.project.model.Order;
import com.sangeet.logisticsystem.project.model.OrderPackage;
import com.sangeet.logisticsystem.project.model.OrderStatus;
import com.sangeet.logisticsystem.project.service.OrderService;
import com.sangeet.logisticsystem.project.service.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class OrderController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private VehicleService vehicleService;

    public Order createOrder(String senderUserID, List<OrderPackage> orderPackages,
                             Location src, Location dest){
        Order order = orderService.createOrder(senderUserID, orderPackages, src, dest);
        this.vehicleService.orderAssigningToVehicle(order);
        return order;
    }

    public String trackOrder(String senderUserID, String orderID){
        return this.orderService.trackOrder(senderUserID, orderID);
    }

    public String updateOrder(String orderID, OrderStatus orderStatus){
        Order order = this.orderService.findOrderByOrderID(orderID);
        this.orderService.updateOrder(orderID, orderStatus);
        this.vehicleService.unassigningOrderFromVehicle(order);
        return order.toString();
    }


}
