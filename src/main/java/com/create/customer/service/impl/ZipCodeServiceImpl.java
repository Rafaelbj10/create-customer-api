package com.create.customer.service.impl;

import com.create.customer.infrastructure.client.viacep.ZipCodeClient;
import com.create.customer.domain.exception.InvalidZipCodeException;
import com.create.customer.infrastructure.client.viacep.ViaCepResponse;
import com.create.customer.service.ZipCodeService;
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

    private final ZipCodeClient zipCodeClient;

    @Override
    public ViaCepResponse getZipCode(final String zipCode) {
        try {
            log.info("Fetching ZIP code information from ViaCEP API for: {}", zipCode);

            final ViaCepResponse response = zipCodeClient.getZipCode(zipCode);

            // Validate if ZIP code was found (ViaCEP returns {"erro": true} for invalid ZIP codes)
            if (response == null || (response.getError() != null && response.getError())) {
                log.warn("Invalid ZIP code: {}. ViaCEP returned error.", zipCode);
                throw new InvalidZipCodeException(zipCode, "ZIP code not found in ViaCEP database");
            }

            log.debug("ZIP code found successfully for: {}", zipCode);
            return response;

        } catch (InvalidZipCodeException e) {
            log.error("Invalid ZIP code: {}", zipCode, e);
            throw e;
        } catch (FeignException.FeignClientException e) {
            log.error("Error querying ZIP code from ViaCEP API: {}", zipCode, e);
            throw new InvalidZipCodeException(zipCode, e);
        } catch (Exception e) {
            log.error("Unexpected error while querying ZIP code: {}", zipCode, e);
            throw new InvalidZipCodeException(zipCode, e);
        }
    }

}



