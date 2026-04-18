package com.create.customer.domain.exception;


public class InvalidZipCodeException extends RuntimeException {


    public InvalidZipCodeException(String zipCode, String message) {
        super("Invalid ZIP code: " + zipCode + " - " + message);
    }

    // mantém o construtor com cause se precisar
    public InvalidZipCodeException(String zipCode, Throwable cause) {
        super("Invalid ZIP code: " + zipCode, cause);
    }
}


