package com.create.customer.application.service.impl;

import com.create.customer.application.service.ClientRegistrationUseCase;
import com.create.customer.domain.exception.UnprocessableEntityException;
import com.create.customer.domain.parameters.ClientRequest;
import com.create.customer.infrastructure.client.ClientDto;
import com.create.customer.application.service.ClientRegistrationService;
import com.create.customer.application.service.ZipCodeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.util.Objects.isNull;

@Slf4j
@RequiredArgsConstructor
@Service
public class ClientRegistrationUseCaseImpl implements ClientRegistrationUseCase {

    private final ClientRegistrationService clientRegistrationService;
    private final ZipCodeService zipCodeService;

    @Override
    public void register(final ClientRequest request) {
        log.info("Starting client registration process for CPF: {}", request.getCpf());
        var cpfExists = validateIfCpfExists(request.getCpf());
        if (isNull(cpfExists)) {
            log.debug("CPF not found, filling address information from ViaCEP");
            fillAddress(request);
            clientRegistrationService.register(request);
            log.info("Client registered successfully");
        } else {
            log.warn("CPF already registered: {}", request.getCpf());
            throw new UnprocessableEntityException("CPF already registered");
        }
    }

    @Override
    public ClientDto findByCpf(final String cpf) {
        log.info("Finding client by CPF: {}", cpf);
        return clientRegistrationService.findByCpfDto(cpf);
    }

    @Override
    public List<ClientDto> findAll() {
        log.info("Fetching all registered clients");
        return clientRegistrationService.findAll();
    }

    @Override
    public int deleteClientByCpf(final String cpf) {
        log.info("Initiating client deletion for CPF: {}", cpf);
        var cpfExists = validateIfCpfExists(cpf);

        if (!isNull(cpfExists)) {
            int result = clientRegistrationService.deleteClientByCpf(cpf);
            log.info("Client deleted successfully");
            return result;
        }
        log.warn("Client not found for deletion with CPF: {}", cpf);
        throw new UnprocessableEntityException("Client not found for deletion");
    }

    private String validateIfCpfExists(final String cpf) {
        log.debug("Validating if CPF exists: {}", cpf);
        return clientRegistrationService.findCpf(cpf);
    }

    private void fillAddress(final ClientRequest request) {
        try {
            log.debug("Filling address from ViaCEP API for ZIP code: {}", request.getZipCode());
            final var zipCodeResponse = zipCodeService.getZipCode(request.getZipCode());
            if (zipCodeResponse != null) {
                String formattedAddress = zipCodeResponse.formatAddress();
                if (formattedAddress != null && !formattedAddress.isEmpty()) {
                    request.setAddress(formattedAddress);
                    log.debug("Address filled successfully: {}", formattedAddress);
                } else {
                    log.warn("ViaCEP returned empty address fields for ZIP code: {}", request.getZipCode());
                }
            }
        } catch (Exception e) {
            log.warn("Error filling address from ViaCEP, continuing without address: {}", e.getMessage());
            // Continue without address information
        }
    }


}

