package br.com.estudos.crud.exception;

/**
 * Exception thrown when there is an error querying the ZIP Code in the ViaCEP API
 */
public class InvalidZipCodeException extends RuntimeException {

    public InvalidZipCodeException(String zipCode) {
        super(String.format("ZIP Code '%s' is invalid or was not found", zipCode));
    }

    public InvalidZipCodeException(String zipCode, Throwable cause) {
        super(String.format("Error querying ZIP Code '%s': %s", zipCode, cause.getMessage()), cause);
    }
}

