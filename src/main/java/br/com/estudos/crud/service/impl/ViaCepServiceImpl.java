package br.com.estudos.crud.service.impl;

import br.com.estudos.crud.clients.ViaCepClient;
import br.com.estudos.crud.presenters.cliente.viacep.ViaCepResponse;
import br.com.estudos.crud.service.ViaCepService;
import feign.FeignException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ViaCepServiceImpl implements ViaCepService {

    private final ViaCepClient viaCepClient;

    public ViaCepResponse getCep(final String cep) {
        try {
            return viaCepClient.getCep(cep);
        } catch (FeignException.FeignClientException e) {
            throw new UnsupportedOperationException("Erro ao consultar cep");
        }
    }


}
