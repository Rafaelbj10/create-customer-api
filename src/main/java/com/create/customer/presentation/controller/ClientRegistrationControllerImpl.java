package com.create.customer.presentation.controller;

import com.create.customer.application.service.ClientRegistrationUseCase;
import com.create.customer.domain.parameters.ClientRequest;
import com.create.customer.infrastructure.client.ClientDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Implementation of ClientRegistrationController
 */
@Slf4j
@RestController
@RequiredArgsConstructor
public class ClientRegistrationControllerImpl implements ClientRegistrationController {

    private final ClientRegistrationUseCase clientRegistrationUseCase;

    @Override
    public ResponseEntity<Void> register(final ClientRequest request) {
        log.info("Registering new client with CPF: {}", request.getCpf());
        clientRegistrationUseCase.register(request);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<ClientDto> findByCpf(final String cpf) {
        log.info("Finding client by CPF: {}", cpf);
        return ResponseEntity.ok(clientRegistrationUseCase.findByCpf(cpf));
    }

    @Override
    public List<ClientDto> findAll() {
        log.info("Fetching all clients");
        return clientRegistrationUseCase.findAll();
    }

    @Override
    public ResponseEntity<Void> deleteByCpf(final String cpf) {
        log.info("Deleting client by CPF: {}", cpf);
        clientRegistrationUseCase.deleteClientByCpf(cpf);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}

