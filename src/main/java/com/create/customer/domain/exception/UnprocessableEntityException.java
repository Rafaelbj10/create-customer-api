package com.create.customer.domain.exception;

/**
 * Exception thrown for unprocessable entity errors
 */
public class UnprocessableEntityException extends RuntimeException {

    public UnprocessableEntityException(String message) {
        super(message);
    }

}

