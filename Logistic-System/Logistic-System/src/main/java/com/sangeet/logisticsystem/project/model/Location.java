package com.sangeet.logisticsystem.project.model;

public class Location {

    private String addressLine1;
    private String addressLine2;
    private String city;
    private String state;
    private String country;
    private Integer pinCode;
    private Double lat;
    private Double lon;

    public Location(String addressLine1, String city, String state, String country,
                    Integer pinCode, Double lat, Double lon) {
        this.addressLine1 = addressLine1;
        this.city = city;
        this.state = state;
        this.country = country;
        this.pinCode = pinCode;
        this.lat = lat;
        this.lon = lon;
    }

    @Override
    public String toString() {
        return "Location{" +
                "addressLine1='" + addressLine1 + '\'' +
                ", state='" + state + '\'' +
                ", country='" + country + '\'' +
                ", pinCode=" + pinCode +
                ", lat=" + lat +
                ", lon=" + lon +
                '}';
    }

    public String getAddressLine1() {
        return addressLine1;
    }

    public String getAddressLine2() {
        return addressLine2;
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

    public Integer getPinCode() {
        return pinCode;
    }

    public Double getLat() {
        return lat;
    }

    public Double getLon() {
        return lon;
    }
}
