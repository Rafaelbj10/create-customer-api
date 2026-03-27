package br.com.estudos.crud.service;

import br.com.estudos.crud.presenters.client.viacep.ViaCepResponse;

/**
 * Service interface for external ZIP code API operations
 */
public interface ZipCodeService {

    ViaCepResponse getZipCode(String zipCode);

}
