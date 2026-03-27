package br.com.estudos.crud.exception;

/**
 * Exception thrown when a client is not found
 */
public class ClientNotFoundException extends RuntimeException {

    public ClientNotFoundException(String cpf) {
        super(String.format("Client with CPF '%s' not found", cpf));
    }

    public ClientNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}

