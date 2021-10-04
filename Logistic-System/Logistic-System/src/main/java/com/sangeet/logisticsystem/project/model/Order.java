package com.sangeet.logisticsystem.project.model;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Order {

    private final String orderID;
    private final List<OrderPackage> packages;
    private Location source;
    private Location destination;
    private OrderStatus orderStatus;
    private final String senderUserID;
    private Vehicle assignedVehicleForDelivery;

    private final Double BASE_PRICE_PER_KG = 100.0;

    public Order(Location source, Location destination, String senderUserID) {
        this.orderID = UUID.randomUUID().toString();
        this.packages = new ArrayList<>();
        this.source = source;
        this.destination = destination;
        this.orderStatus = orderStatus.CREATED;
        this.senderUserID = senderUserID;
        this.assignedVehicleForDelivery = null;
    }

    public Double getOrderAmount(){
        Double packageWeight = getPackageWeightPerOrder();
        Double orderAmount = 0.0;

        if(packageWeight >= 0.0 && packageWeight <= 1.0){
            orderAmount += BASE_PRICE_PER_KG * packageWeight;
            //tax
            Double gstAmount = orderAmount * 0.12;
            orderAmount += gstAmount;
        }else if(packageWeight >= 1.1 && packageWeight <= 10.0){
            orderAmount += BASE_PRICE_PER_KG * packageWeight;
            //tax
            Double gstAmount = orderAmount * 0.15;
            orderAmount += gstAmount;
        }else {
            orderAmount += BASE_PRICE_PER_KG * packageWeight;
            //tax
            Double gstAmount = orderAmount * 0.20;
            orderAmount += gstAmount;
        }

        return orderAmount;
    }

    public Double getPackageWeightPerOrder(){
        Double packageWeight = 0.0;
        for(OrderPackage pack: packages){
            packageWeight += pack.getPackageWeight();
        }
        return packageWeight;
    }

    public void addPackagesToOrder(List<OrderPackage> packages){
        this.packages.addAll(packages);
    }

    public String getOrderID() {
        return orderID;
    }

    public Location getSource() {
        return source;
    }

    public Location getDestination() {
        return destination;
    }

    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    public String getSenderUserID() {
        return senderUserID;
    }

    public void setOrderStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }

    public Vehicle getAssignedVehicleForDelivery() {
        return assignedVehicleForDelivery;
    }

    public void setAssignedVehicleForDelivery(Vehicle assignedVehicleForDelivery) {
        this.assignedVehicleForDelivery = assignedVehicleForDelivery;
    }

    @Override
    public String toString() {
        return "Order{" +
                "orderID='" + orderID + '\'' +
                ", source=" + source +
                ", destination=" + destination +
                ", orderStatus=" + orderStatus +
                ", senderUserID='" + senderUserID + '\'' +
                ", totalWeight='" + getPackageWeightPerOrder() + '\'' +
                ", orderAmount='" + getOrderAmount() + '\'' +
                ", vehicle='" + (getAssignedVehicleForDelivery() != null
                ? getAssignedVehicleForDelivery().getVehicleNo()
                : "null") + '\'' +
                '}';
    }
}
