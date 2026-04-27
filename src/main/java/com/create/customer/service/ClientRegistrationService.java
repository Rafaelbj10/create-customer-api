package com.create.customer.service;

import com.create.customer.domain.parameters.ClientRequest;

import java.util.UUID;

public interface ClientRegistrationService {

    Long insertClient(final ClientRequest request, UUID externalId);

    String findCpf(String cpf);

}

