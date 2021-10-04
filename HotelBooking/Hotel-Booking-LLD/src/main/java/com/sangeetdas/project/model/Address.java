package com.sangeetdas.project.model;

public class Address {

    private String addressLineOne;
    private String addressLineTwo;
    private String addressLineThree;
    private String city;
    private String state;
    private String country;
    private String pin;

    public Address(String addressLineOne, String city, String state, String country, String pin) {
        this.addressLineOne = addressLineOne;
        this.city = city;
        this.state = state;
        this.country = country;
        this.pin = pin;
    }

    public String getAddressLineOne() {
        return addressLineOne;
    }

    public String getAddressLineTwo() {
        return addressLineTwo;
    }

    public String getAddressLineThree() {
        return addressLineThree;
    }

    public String getCity() {
        return city;
    }

    public String getState() {
        return state;
    }

    public String getCountry() {
        return country;
    }

    public String getPin() {
        return pin;
    }
}
