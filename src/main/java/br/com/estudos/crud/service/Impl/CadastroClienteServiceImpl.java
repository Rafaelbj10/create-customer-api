package br.com.estudos.crud.service.Impl;

import br.com.estudos.crud.parameters.ClienteRequest;
import br.com.estudos.crud.presenters.cliente.ClienteDto;
import br.com.estudos.crud.repository.ClienteRepository;
import br.com.estudos.crud.service.CadastroClienteService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CadastroClienteServiceImpl implements CadastroClienteService {

    private final ClienteRepository clienteRepository;

    public void cadastrar(final ClienteRequest request) {
        clienteRepository.insertClient(request);
    }

    @Override
    public ClienteDto findByCpf(final String cpf) {
        return clienteRepository.findByCpf(cpf);
    }

    @Override
    public List<ClienteDto> findAll() {
        return clienteRepository.findAll();
    }

    public void deleteClienteByCpf(final String cpf) {
        clienteRepository.deleteClientByCpf(cpf);
    }

}
