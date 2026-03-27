package br.com.estudos.crud.service.impl;

import br.com.estudos.crud.model.Client;
import br.com.estudos.crud.parameters.ClientRequest;
import br.com.estudos.crud.presenters.client.ClientDto;
import br.com.estudos.crud.repository.ClientRepository;
import br.com.estudos.crud.service.ClientRegistrationService;
import br.com.estudos.crud.utils.queries.mappers.ClientMapper;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Implementation of ClientRegistrationService
 */
@Service
@RequiredArgsConstructor
@Transactional
public class ClientRegistrationServiceImpl implements ClientRegistrationService {

    private final ClientRepository clientRepository;

    @Override
    public Long register(final ClientRequest request) {
        return clientRepository.insertClient(request);
    }

    @Override
    public ClientDto findByCpfDto(final String cpf) {
        final var client = clientRepository.findByCpf(cpf);
        return ClientMapper.mapClient(client);
    }

    @Override
    public Client findByCpf(final String cpf) {
        return clientRepository.findByCpf(cpf);
    }

    @Override
    public List<ClientDto> findAll() {
        return clientRepository.findAll();
    }

    @Override
    public String findCpf(final String cpf) {
        return clientRepository.findCpf(cpf);
    }

    @Override
    public int deleteClientByCpf(final String cpf) {
        return clientRepository.deleteClientByCpf(cpf);
    }

}
