package com.miguel.demo.exceptions;

public class ActionNotPossibleException extends RuntimeException {
    public ActionNotPossibleException(String message) {
        super(message);
    }
}
