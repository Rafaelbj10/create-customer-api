package br.com.estudos.crud.exception;

/**
 * Exceção lançada quando há erro ao consultar CEP na API ViaCEP
 */
public class CepInvalidoException extends RuntimeException {

    public CepInvalidoException(String cep) {
        super(String.format("CEP '%s' é inválido ou não foi encontrado", cep));
    }

    public CepInvalidoException(String cep, Throwable cause) {
        super(String.format("Erro ao consultar CEP '%s': %s", cep, cause.getMessage()), cause);
    }
}

