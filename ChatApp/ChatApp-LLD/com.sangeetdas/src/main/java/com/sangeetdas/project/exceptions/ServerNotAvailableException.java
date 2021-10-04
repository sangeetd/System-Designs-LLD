package com.sangeetdas.project.exceptions;

public class ServerNotAvailableException extends RuntimeException{
    public ServerNotAvailableException() {
        super("Server connectivity not available at the moment");
    }
}
