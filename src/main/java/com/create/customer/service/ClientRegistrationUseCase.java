package com.create.customer.service;

import com.create.customer.domain.parameters.ClientRequest;
import com.create.customer.infrastructure.client.ClientDto;

import java.util.List;

public interface ClientRegistrationUseCase {

    void register(ClientRequest request);

}

