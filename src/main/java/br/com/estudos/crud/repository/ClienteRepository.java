package br.com.estudos.crud.repository;

import br.com.estudos.crud.model.Cliente;
import br.com.estudos.crud.parameters.ClienteRequest;

import java.util.List;

public interface ClienteRepository {

    Long insertClient(ClienteRequest request);

    Cliente findByCpf(String cpf);

    String findCpf(String cpf);

    List<Cliente> findAll();

    int deleteClientByCpf(String cpf);

}

