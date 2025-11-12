package com.kata.rover.exception;

public class TechnicalException extends RuntimeException {

    public TechnicalException(String message, Throwable cause) {
        super(message, cause);
    }
}
