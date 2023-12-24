package com.nookure.chat.api.exceptions;

public class ServiceNotFoundException extends Exception {
    public ServiceNotFoundException(String message) {
        super(message);
    }
}
