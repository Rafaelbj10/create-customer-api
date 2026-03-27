package br.com.estudos.crud.controller.impl;

import br.com.estudos.crud.business.ClientRegistrationBusiness;
import br.com.estudos.crud.controller.ClientRegistrationController;
import br.com.estudos.crud.parameters.ClientRequest;
import br.com.estudos.crud.presenters.client.ClientDto;
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

    private final ClientRegistrationBusiness clientRegistrationBusiness;

    @Override
    public ResponseEntity<Void> register(final ClientRequest request) {
        log.info("Registering new client with CPF: {}", request.getCpf());
        clientRegistrationBusiness.register(request);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<ClientDto> findByCpf(final String cpf) {
        log.info("Finding client by CPF: {}", cpf);
        return ResponseEntity.ok(clientRegistrationBusiness.findByCpf(cpf));
    }

    @Override
    public List<ClientDto> findAll() {
        log.info("Fetching all clients");
        return clientRegistrationBusiness.findAll();
    }

    @Override
    public ResponseEntity<Void> deleteByCpf(final String cpf) {
        log.info("Deleting client by CPF: {}", cpf);
        clientRegistrationBusiness.deleteClientByCpf(cpf);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
