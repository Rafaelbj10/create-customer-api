package com.create.customer.application.service;

import com.create.customer.domain.parameters.ClientRequest;
import com.create.customer.infrastructure.client.ClientDto;

import java.util.List;

/**
 * Use case interface for client registration operations
 */
public interface ClientRegistrationUseCase {

    void register(ClientRequest request);

    ClientDto findByCpf(String cpf);

    List<ClientDto> findAll();

    int deleteClientByCpf(String cpf);

}

