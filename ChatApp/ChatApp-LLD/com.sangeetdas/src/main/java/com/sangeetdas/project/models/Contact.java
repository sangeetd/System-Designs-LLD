package com.sangeetdas.project.models;

import java.util.UUID;

public class Contact {

    private String id;
    private String contactNo;
    private String firstName;
    private String lastName;

    public Contact(String contactNo, String firstName, String lastName) {
        this.id = UUID.randomUUID().toString();
        this.contactNo = contactNo;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public Contact(String contactNo, String firstName) {
        this.id = UUID.randomUUID().toString();
        this.contactNo = contactNo;
        this.firstName = firstName;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getContactNo() {
        return contactNo;
    }

    public void setContactNo(String contactNo) {
        this.contactNo = contactNo;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}
