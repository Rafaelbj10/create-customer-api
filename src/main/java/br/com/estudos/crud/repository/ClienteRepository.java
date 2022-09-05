package br.com.estudos.crud.repository;

import br.com.estudos.crud.parameters.ClienteRequest;
import br.com.estudos.crud.presenters.cliente.ClienteDto;

import java.util.List;

public interface ClienteRepository {

    Long insertClient(ClienteRequest request);

    ClienteDto findByCpf(String cpf);

    List<ClienteDto> findAll();

    int deleteClientByCpf(String cpf);


}

