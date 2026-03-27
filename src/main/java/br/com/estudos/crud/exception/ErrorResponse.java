package br.com.estudos.crud.exception;

import lombok.Getter;

/**
 * Standard error response model
 */
@Getter
public class ErrorResponse {

    private String message;

    public ErrorResponse(String message) {
        this.message = message;
    }

}
