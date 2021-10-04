package com.sangeet.logisticsystem.project.model;

import java.util.UUID;

public class Item {
    private String itemID;
    private String itemName;
    private Double itemPrice;
    private Double itemWeight;

    public Item(String itemName, Double itemPrice, Double itemWeight) {
        this.itemID = UUID.randomUUID().toString();
        this.itemName = itemName;
        this.itemPrice = itemPrice;
        this.itemWeight = itemWeight;
    }

    public String getItemID() {
        return itemID;
    }

    public String getItemName() {
        return itemName;
    }

    public Double getItemPrice() {
        return itemPrice;
    }

    public Double getItemWeight() {
        return itemWeight;
    }
}
