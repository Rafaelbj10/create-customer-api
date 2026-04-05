package com.create.customer.application.service;

import com.create.customer.infrastructure.client.viacep.ViaCepResponse;

/**
 * Service interface for external ZIP code API operations
 */
public interface ZipCodeService {

    ViaCepResponse getZipCode(String zipCode);

}

