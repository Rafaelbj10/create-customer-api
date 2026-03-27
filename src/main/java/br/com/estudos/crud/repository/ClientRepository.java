package br.com.estudos.crud.repository;

import br.com.estudos.crud.model.Client;
import br.com.estudos.crud.parameters.ClientRequest;
import br.com.estudos.crud.presenters.client.ClientDto;

import java.util.List;

/**
 * Repository interface for client data access operations
 */
public interface ClientRepository {

    Long insertClient(ClientRequest request);

    Client findByCpf(String cpf);

    String findCpf(String cpf);

    List<ClientDto> findAll();

    int deleteClientByCpf(String cpf);

}

