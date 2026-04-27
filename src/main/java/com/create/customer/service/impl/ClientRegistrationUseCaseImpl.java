package com.create.customer.service.impl;

import com.create.customer.domain.exception.UnprocessableEntityException;
import com.create.customer.domain.parameters.ClientRequest;
import com.create.customer.events.CustomerCreatedEvent;
import com.create.customer.service.ClientRegistrationService;
import com.create.customer.service.ClientRegistrationUseCase;
import com.create.customer.service.SqsService;
import com.create.customer.service.ZipCodeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

import static java.util.Objects.isNull;

@Slf4j
@RequiredArgsConstructor
@Service
public class ClientRegistrationUseCaseImpl implements ClientRegistrationUseCase {

    private final ClientRegistrationService clientRegistrationService;
    private final ZipCodeService zipCodeService;
    private final SqsService sqsService;

    @Override
    public void register(final ClientRequest request) {
        log.info("Starting client registration process for CPF: {}", request.getCpf());
        var cpfExists = validateIfCpfExists(request.getCpf());
        if (isNull(cpfExists)) {
            log.debug("CPF not found, filling address information from ViaCEP");
            fillAddress(request);

            UUID externalId = UUID.randomUUID();
            final Long customerId = clientRegistrationService.insertClient(request, externalId);

            CustomerCreatedEvent event = new CustomerCreatedEvent();
            event.setCustomerId(externalId);
            event.setCpf(request.getCpf());
            event.setMonthlyIncome(request.getMonthlyIncome());
            event.setTimestamp(LocalDateTime.now());

            sqsService.sendCustomerCreatedEvent(event);
            log.info("CustomerCreatedEvent sent to SQS after registration for customerId: {}", customerId);

            log.info("Client registered successfully");
        } else {
            log.warn("CPF already registered: {}", request.getCpf());
            throw new UnprocessableEntityException("CPF already registered");
        }
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

