package br.com.estudos.crud.business;

import br.com.estudos.crud.parameters.ClientRequest;
import br.com.estudos.crud.presenters.client.ClientDto;

import java.util.List;

/**
 * Business interface for client registration operations
 */
public interface ClientRegistrationBusiness {

    void register(ClientRequest request);

    ClientDto findByCpf(String cpf);

    List<ClientDto> findAll();

    int deleteClientByCpf(String cpf);

}
