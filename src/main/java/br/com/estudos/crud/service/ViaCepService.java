package br.com.estudos.crud.service;

import br.com.estudos.crud.presenters.cliente.viacep.ViaCepResponse;

public interface ViaCepService {

    ViaCepResponse getCep(String cep);

}
