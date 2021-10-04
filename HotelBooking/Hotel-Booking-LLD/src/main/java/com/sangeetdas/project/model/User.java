package com.sangeetdas.project.model;

import java.util.UUID;

public class User {

    private String id;
    private String name;
    private String email;
    private String password;

    public User(String name, String email, String password) {
        this.id = UUID.randomUUID().toString();
        this.name = name;
        this.email = email;
        this.password = password;
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

    public String getPassword() {
        return password;
    }
}
