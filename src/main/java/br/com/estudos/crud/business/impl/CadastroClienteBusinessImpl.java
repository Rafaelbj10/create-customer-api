package br.com.estudos.crud.business.impl;


import br.com.estudos.crud.business.CadastroClienteBusiness;
import br.com.estudos.crud.exception.UnprocessableEntityException;
import br.com.estudos.crud.parameters.ClienteRequest;
import br.com.estudos.crud.presenters.cliente.ClienteDto;
import br.com.estudos.crud.presenters.cliente.viacep.ViaCepResponse;
import br.com.estudos.crud.service.CadastroClienteService;
import br.com.estudos.crud.service.ViaCepService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.util.Objects.isNull;

@RequiredArgsConstructor
@Service
public class CadastroClienteBusinessImpl implements CadastroClienteBusiness {

    private final CadastroClienteService cadastroClienteService;
    private final ViaCepService viaCepService;

    public void cadastrar(final ClienteRequest request) {

        validCpf(request.getCpf());

        fillAdreess(request);
        cadastroClienteService.cadastrar(request);
    }

    public void validCpf(final String cpf) {

        final var validCpf = cadastroClienteService.findByCpf(cpf);

        if (!isNull(validCpf.getCpf())) {
            throw new UnprocessableEntityException("CPF já cadastrado");
        }

    }

    public ViaCepResponse fillAdreess(final ClienteRequest request) {
        final var cepRequest = viaCepService.getCep(request.getCep());
        request.setAddress(cepRequest.toString());
        return cepRequest;
    }

    public ClienteDto findByCpf(final String cpf) {
        return cadastroClienteService.findByCpfDto(cpf);
    }

    public List<ClienteDto> findAll() {
        return cadastroClienteService.findAll();
    }

    public int deleteClienteByCpf(final String cpf) {

        validCpf(cpf);

        return cadastroClienteService.deleteClienteByCpf(cpf);
    }


}
