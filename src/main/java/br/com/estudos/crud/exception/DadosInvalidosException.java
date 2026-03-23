package br.com.estudos.crud.exception;

/**
 * Exceção lançada quando há dados inválidos na requisição
 */
public class DadosInvalidosException extends RuntimeException {

    public DadosInvalidosException(String message) {
        super(message);
    }

    public DadosInvalidosException(String message, Throwable cause) {
        super(message, cause);
    }
}

