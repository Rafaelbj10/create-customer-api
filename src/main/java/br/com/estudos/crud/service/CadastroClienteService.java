package br.com.estudos.crud.service;

import br.com.estudos.crud.parameters.ClienteRequest;
import br.com.estudos.crud.presenters.cliente.ClienteDto;

import java.util.List;

public interface CadastroClienteService {

    void cadastrar(ClienteRequest request);

    ClienteDto findByCpf(String cpf);

    List<ClienteDto> findAll();

    int deleteClienteById(String cpf);

}
