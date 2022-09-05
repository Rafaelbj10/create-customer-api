package br.com.estudos.crud.exception;

public class UnprocessableEntityException extends RuntimeException {

    public UnprocessableEntityException(String message){
        super(message);
    }

}
