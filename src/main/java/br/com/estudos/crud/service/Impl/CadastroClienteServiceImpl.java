package br.com.estudos.crud.service.Impl;

import br.com.estudos.crud.model.Cliente;
import br.com.estudos.crud.repository.ClienteRepository;
import br.com.estudos.crud.service.CadastroClienteService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CadastroClienteServiceImpl implements CadastroClienteService {

    private final ClienteRepository clienteRepository;

    public void cadastrar(final Cliente cliente) {
        clienteRepository.insertClient(cliente);
    }

    @Override
    public Cliente buscar(final String cpf) {
        return clienteRepository.buscar(cpf);
    }

}
