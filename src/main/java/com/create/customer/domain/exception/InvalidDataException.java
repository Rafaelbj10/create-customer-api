package com.create.customer.domain.exception;

/**
 * Exception thrown when there is invalid data in the request
 */
public class InvalidDataException extends RuntimeException {

    public InvalidDataException(String message) {
        super(message);
    }

    public InvalidDataException(String message, Throwable cause) {
        super(message, cause);
    }
}


