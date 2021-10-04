package com.sangeet.logisticsystem.project.model;

import java.util.UUID;

public class User {

    private final String userID;
    private String email;
    private String mobileNumber;
    private String username;

    public User(String email, String mobileNumber, String username) {
        this.userID = UUID.randomUUID().toString();
        this.email = email;
        this.mobileNumber = mobileNumber;
        this.username = username;
    }

    public String getUserID() {
        return userID;
    }
}
