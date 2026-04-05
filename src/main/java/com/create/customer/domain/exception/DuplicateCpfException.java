package com.create.customer.domain.exception;

/**
 * Exception thrown when a CPF is already registered
 */
public class DuplicateCpfException extends RuntimeException {

    public DuplicateCpfException(String cpf) {
        super(String.format("CPF '%s' is already registered", cpf));
    }

    public DuplicateCpfException(String message, Throwable cause) {
        super(message, cause);
    }
}


