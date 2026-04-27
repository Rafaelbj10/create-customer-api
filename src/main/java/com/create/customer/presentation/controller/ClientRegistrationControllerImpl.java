package com.create.customer.presentation.controller;

import com.create.customer.service.ClientRegistrationUseCase;
import com.create.customer.domain.parameters.ClientRequest;
import com.create.customer.infrastructure.client.ClientDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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

}

