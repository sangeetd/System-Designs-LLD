package com.sangeet.project.model;

import java.util.UUID;

public class User {

    private String id;
    private String name;
    private String email;
    private String mobile;

    public User(String name, String email, String mobile) {
        this.id = UUID.randomUUID().toString();
        this.name = name;
        this.email = email;
        this.mobile = mobile;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getMobile() {
        return mobile;
    }
}
