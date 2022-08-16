package br.com.estudos.crud.clients;

import br.com.estudos.crud.presenters.cliente.viacep.ViaCepResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(value = "viacep-consumer", url = "https://viacep.com.br/")
public interface ViaCepClient {

    @GetMapping(value = "/ws/{cep}/json")
    ViaCepResponse getCep(@PathVariable("cep") String cep);

}
