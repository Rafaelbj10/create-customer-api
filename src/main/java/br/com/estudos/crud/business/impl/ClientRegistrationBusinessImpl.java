package br.com.estudos.crud.business.impl;

import br.com.estudos.crud.business.ClientRegistrationBusiness;
import br.com.estudos.crud.exception.UnprocessableEntityException;
import br.com.estudos.crud.parameters.ClientRequest;
import br.com.estudos.crud.presenters.client.ClientDto;
import br.com.estudos.crud.presenters.client.viacep.ViaCepResponse;
import br.com.estudos.crud.service.ClientRegistrationService;
import br.com.estudos.crud.service.ViaCepService;
import br.com.estudos.crud.utils.queries.mappers.ClientMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.util.Objects.isNull;

/**
 * Implementation of ClientRegistrationBusiness
 */
@Slf4j
@RequiredArgsConstructor
@Service
public class ClientRegistrationBusinessImpl implements ClientRegistrationBusiness {

    private final ClientRegistrationService clientRegistrationService;
    private final ViaCepService viaCepService;

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
        return ClientMapper.map(clientRegistrationService.findAll());
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

    private ViaCepResponse fillAddress(final ClientRequest request) {
        try {
            log.debug("Filling address from ViaCEP API for ZIP code: {}", request.getZipCode());
            final var zipCodeResponse = zipCodeService.getZipCode(request.getZipCode());
            request.setAddress(zipCodeResponse.toString());
            return zipCodeResponse;
        } catch (Exception e) {
            log.warn("Error filling address from ViaCEP, continuing without address: {}", e.getMessage());
            // Continue without address information
            return null;
        }
    }


}
