package com.sangeetdas.project.exceptions;

public class UserNotRegisteredException extends RuntimeException{

    public UserNotRegisteredException() {
        super("The user you want to send this message is not registered with us. Kindly invite them to use [AppName]");
    }
}

