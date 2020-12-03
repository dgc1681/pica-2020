package com.touresbalon.microservices.checkout.exception;

public class CustomException extends RuntimeException {

    public CustomException(String message) {
        super(message);
    }
}
