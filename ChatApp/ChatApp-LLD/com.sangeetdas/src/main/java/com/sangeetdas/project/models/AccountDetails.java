package com.sangeetdas.project.models;

public class AccountDetails {
    private Contact userContact;

    public AccountDetails(Contact userContact) {
        this.userContact = userContact;
    }

    public Contact getUserContact() {
        return userContact;
    }
}
