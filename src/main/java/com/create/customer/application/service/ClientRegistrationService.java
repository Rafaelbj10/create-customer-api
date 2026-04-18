package com.create.customer.application.service;

import com.create.customer.domain.model.Client;
import com.create.customer.domain.parameters.ClientRequest;
import com.create.customer.infrastructure.client.ClientDto;

import java.util.List;

/**
 * Service interface for client registration operations
 */
public interface ClientRegistrationService {

    Long register(ClientRequest request);

    ClientDto findByCpfDto(String cpf);

    Client findByCpf(String cpf);

    String findCpf(String cpf);

    List<ClientDto> findAll();

    int deleteClientByCpf(String cpf);

}

