package com.miguel.demo.exceptions;

public class ScooterNotFoundException extends RuntimeException{
    public ScooterNotFoundException(String message) {
        super(message);
    }
}
