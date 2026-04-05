package com.create.customer.infrastructure.repository;

import com.create.customer.domain.model.Client;
import com.create.customer.domain.parameters.ClientRequest;
import com.create.customer.infrastructure.client.ClientDto;

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

