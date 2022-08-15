package br.com.estudos.crud.business.impl;


import br.com.estudos.crud.business.CadastroClienteBusiness;
import br.com.estudos.crud.parameters.ClienteRequest;
import br.com.estudos.crud.presenters.cliente.ClienteDto;
import br.com.estudos.crud.service.CadastroClienteService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class CadastroClienteBusinessImpl implements CadastroClienteBusiness {

    private final CadastroClienteService cadastroClienteService;

    public void cadastrar(final ClienteRequest request) {
        cadastroClienteService.cadastrar(request);
    }

    public ClienteDto findByCpf(final String cpf) {
        return cadastroClienteService.findByCpf(cpf);
    }

    public List<ClienteDto> findAll() {
        return cadastroClienteService.findAll();
    }

    public void deleteClienteByCpf(final String cpf) {
        cadastroClienteService.deleteClienteByCpf(cpf);
    }


}
