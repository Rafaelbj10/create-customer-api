package com.create.customer.infrastructure.repository;

import com.create.customer.domain.model.Customer;
import com.create.customer.domain.parameters.ClientRequest;
import com.create.customer.infrastructure.client.ClientDto;

import java.util.List;
import java.util.UUID;

public interface CustomerRepository {

    UUID insertClient(ClientRequest request, UUID externalId);

    Customer findByCpf(String cpf);

    String findCpf(String cpf);

    List<ClientDto> findAll();

    int deleteClientByCpf(String cpf);
}