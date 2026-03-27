package br.com.estudos.crud.exception;

/**
 * Exception thrown for unprocessable entity errors
 */
public class UnprocessableEntityException extends RuntimeException {

    public UnprocessableEntityException(String message) {
        super(message);
    }

}
