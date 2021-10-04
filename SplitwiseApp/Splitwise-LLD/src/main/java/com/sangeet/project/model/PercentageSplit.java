package com.sangeet.project.model;

public class PercentageSplit extends Split{

    private Double percentage;

    public PercentageSplit(User user, Double percentage) {
        super(user);
        this.percentage = percentage;
    }

    public Double getPercentage() {
        return percentage;
    }

    public void setPercentage(Double percentage) {
        this.percentage = percentage;
    }
}
