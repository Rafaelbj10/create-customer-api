package com.create.customer.infrastructure.client;

import com.create.customer.infrastructure.client.viacep.ViaCepResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * Feign client for ViaCEP API integration
 * Provides ZIP code lookup functionality
 */
@FeignClient(
    name = "viacep-client",
    url = "https://viacep.com.br",
    configuration = ViaCepClientConfiguration.class
)
public interface ZipCodeClient {

    /**
     * Fetches address information for a given ZIP code
     * @param zipCode Brazilian ZIP code (format: 12345-678 or 12345678)
     * @return ViaCepResponse with address information
     */
    @GetMapping(value = "/ws/{zipCode}/json")
    ViaCepResponse getZipCode(@PathVariable("zipCode") String zipCode);

}

