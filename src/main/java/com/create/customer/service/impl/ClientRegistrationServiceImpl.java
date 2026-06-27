package com.create.customer.service.impl;

import com.create.customer.domain.parameters.ClientRequest;
import com.create.customer.infrastructure.repository.CustomerRepository;
import com.create.customer.service.ClientRegistrationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ClientRegistrationServiceImpl implements ClientRegistrationService {

    private final CustomerRepository customerRepository;

    @Override
    public UUID insertClient(ClientRequest request, UUID externalId) {
        return customerRepository.insertClient(request, externalId);
    }

    @Override
    public String findCpf(final String cpf) {
        return customerRepository.findCpf(cpf);
    }
}