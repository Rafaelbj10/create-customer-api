package br.com.estudos.crud.business.impl;


import br.com.estudos.crud.business.CadastroClienteBusiness;
import br.com.estudos.crud.parameters.ClienteRequest;
import br.com.estudos.crud.presenters.cliente.ClienteDto;
import br.com.estudos.crud.service.CadastroClienteService;
import br.com.estudos.crud.service.ViaCepService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class CadastroClienteBusinessImpl implements CadastroClienteBusiness {

    private final CadastroClienteService cadastroClienteService;
    private final ViaCepService viaCepService;

    public void cadastrar(final ClienteRequest request) {
        final var addressClient = viaCepService.getCep(request.getCep());
        request.setAddress(addressClient.toString());
        cadastroClienteService.cadastrar(request);
    }

    public ClienteDto findByCpf(final String cpf) {
        return cadastroClienteService.findByCpf(cpf);
    }

    public List<ClienteDto> findAll() {
        return cadastroClienteService.findAll();
    }

    public int deleteClienteByCpf(final String cpf) {
        return cadastroClienteService.deleteClienteByCpf(cpf);
    }


}
