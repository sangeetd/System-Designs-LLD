package com.sangeet.logisticsystem.project.service;

import com.sangeet.logisticsystem.project.model.Location;
import com.sangeet.logisticsystem.project.model.Order;
import com.sangeet.logisticsystem.project.model.OrderPackage;
import com.sangeet.logisticsystem.project.model.OrderStatus;
import com.sangeet.logisticsystem.project.repo.OrderRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {

    @Autowired
    private OrderRepo orderRepo;

    public Order createOrder(String senderUserID, List<OrderPackage> orderPackages,
                             Location src, Location dest){
        Order newOrder = new Order(src, dest, senderUserID);
        newOrder.addPackagesToOrder(orderPackages);
        this.orderRepo.save(newOrder);
        return newOrder;
    }

    public String trackOrder(String senderUserID, String orderID) {
        return this.orderRepo.findOrderStatus(senderUserID, orderID);
    }

    public void updateOrder(String orderID, OrderStatus orderStatus) {
        this.orderRepo.updateOrder(orderID, orderStatus);
    }

    public Order findOrderByOrderID(String orderID){
        return this.orderRepo.findOrderByOrderID(orderID);
    }

}
