package br.com.estudos.crud.service;

import br.com.estudos.crud.model.Client;
import br.com.estudos.crud.parameters.ClientRequest;
import br.com.estudos.crud.presenters.client.ClientDto;

import java.util.List;

/**
 * Service interface for client registration operations
 */
public interface ClientRegistrationService {

    Long register(ClientRequest request);

    ClientDto findByCpfDto(String cpf);

    Client findByCpf(String cpf);

    String findCpf(String cpf);

    List<ClientDto> findAll();

    int deleteClientByCpf(String cpf);

}
