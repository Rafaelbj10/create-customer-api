package br.com.estudos.crud.repository;

import br.com.estudos.crud.parameters.ClienteRequest;
import br.com.estudos.crud.presenters.cliente.ClienteDto;

import java.util.List;

public interface ClienteRepository {

    void insertClient(ClienteRequest request);

    ClienteDto findByCpf(String cpf);

    List<ClienteDto> findAll();


}

