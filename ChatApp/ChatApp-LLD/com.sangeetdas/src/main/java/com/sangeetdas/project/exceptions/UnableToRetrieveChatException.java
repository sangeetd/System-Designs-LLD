package com.sangeetdas.project.exceptions;

public class UnableToRetrieveChatException extends RuntimeException{
    public UnableToRetrieveChatException(String contactNo) {
        super("Unable to retrieve chat for contact: "+contactNo);
    }
}
