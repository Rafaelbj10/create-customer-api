package br.com.estudos.crud.clients;

import br.com.estudos.crud.presenters.client.viacep.ViaCepResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * Feign client for ViaCEP ZIP code API
 */
@FeignClient(value = "zipcode-consumer", url = "https://viacep.com.br/")
public interface ZipCodeClient {

    @GetMapping(value = "/ws/{zipCode}/json")
    ViaCepResponse getZipCode(@PathVariable("zipCode") String zipCode);

}
