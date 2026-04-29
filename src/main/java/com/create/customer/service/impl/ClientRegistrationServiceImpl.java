package com.create.customer.service.impl;

import com.create.customer.domain.parameters.ClientRequest;
import com.create.customer.infrastructure.repository.ClientRepository;
import com.create.customer.service.ClientRegistrationService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional
public class ClientRegistrationServiceImpl implements ClientRegistrationService {

    private final ClientRepository clientRepository;

    @Override
    public Long insertClient(ClientRequest request, UUID externalId) {
        return clientRepository.insertClient(request, externalId);
    }

    @Override
    public String findCpf(final String cpf) {
        return clientRepository.findCpf(cpf);
    }

}

