package com.sangeet.project.model;

public abstract class Split {

    private User user;
    private Double amount;

    public Split(User user) {
        this.user = user;
    }

    public Split(User user, Double amount) {
        this.user = user;
        this.amount = amount;
    }

    public User getUser() {
        return user;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }
}
