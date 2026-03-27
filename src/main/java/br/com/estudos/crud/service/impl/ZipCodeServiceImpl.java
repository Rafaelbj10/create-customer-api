package br.com.estudos.crud.service.impl;

import br.com.estudos.crud.clients.ViaCepClient;
import br.com.estudos.crud.exception.InvalidZipCodeException;
import br.com.estudos.crud.presenters.client.viacep.ViaCepResponse;
import br.com.estudos.crud.service.ZipCodeService;
import feign.FeignException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * Implementation of ZipCodeService
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class ZipCodeServiceImpl implements ZipCodeService {

    private final ViaCepClient viaCepClient;

    @Override
    public ViaCepResponse getZipCode(final String zipCode) {
        try {
            log.info("Fetching ZIP code information from ViaCEP API for: {}", zipCode);
            return zipCodeClient.getZipCode(zipCode);
        } catch (FeignException.FeignClientException e) {
            log.error("Error querying ZIP code from ViaCEP API: {}", zipCode, e);
            throw new InvalidZipCodeException(zipCode, e);
        } catch (Exception e) {
            log.error("Unexpected error while querying ZIP code: {}", zipCode, e);
            throw new InvalidZipCodeException(zipCode, e);
        }
    }


}
