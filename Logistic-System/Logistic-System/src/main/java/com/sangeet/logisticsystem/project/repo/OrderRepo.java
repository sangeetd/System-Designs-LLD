package com.sangeet.logisticsystem.project.repo;

import com.sangeet.logisticsystem.project.model.Order;
import com.sangeet.logisticsystem.project.model.OrderStatus;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class OrderRepo {

    private List<Order> orderList;

    public OrderRepo() {
        this.orderList = new ArrayList<>();
    }

    public void save(Order order){
        orderList.add(order);
    }

    public String findOrderStatus(String senderUserID, String orderID) {
        for(Order order: orderList){
            if(order.getOrderID().equals(orderID) && order.getSenderUserID().equals(senderUserID)){
                return order.getOrderStatus().name();
            }
        }

        return "Exception: No corresponding order found for the given user ID: "+senderUserID;
    }

    public void updateOrder(String orderID, OrderStatus orderStatus) {
        for(Order order: orderList){
            if(order.getOrderID().equals(orderID)){
                order.setOrderStatus(orderStatus);
            }
        }

//        return "Exception: No corresponding order found for the given user ID";
    }

    public Order findOrderByOrderID(String orderID) {

        for(Order order: orderList){
            if(order.getOrderID().equals(orderID)){
                return order;
            }
        }
        return null;
    }
}
