package br.com.estudos.crud.business;

import br.com.estudos.crud.parameters.ClienteRequest;
import br.com.estudos.crud.presenters.cliente.ClienteDto;

import java.util.List;

public interface CadastroClienteBusiness {

    void cadastrar(ClienteRequest request);

    ClienteDto findByCpf(String cpf);

    List<ClienteDto> findAll();

    void deleteClienteByCpf(String cpf);

}
