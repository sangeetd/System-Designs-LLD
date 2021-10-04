package com.sangeet.logisticsystem.project.model;

import java.util.ArrayList;
import java.util.List;

public class OrderPackage {

    private final List<Item> items;

    public OrderPackage() {
        this.items = new ArrayList<>();
    }

    public void addItemsToPackage(List<Item> items){
        this.items.addAll(items);
    }

    public Double getPackageWeight(){
        Double totalWeight = 0.0;
        for(Item item : items){
            totalWeight += item.getItemWeight();
        }
        return totalWeight;
    }

}
