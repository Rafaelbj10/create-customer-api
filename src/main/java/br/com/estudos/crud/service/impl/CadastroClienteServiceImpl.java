package br.com.estudos.crud.service.impl;

import br.com.estudos.crud.model.Cliente;
import br.com.estudos.crud.parameters.ClienteRequest;
import br.com.estudos.crud.presenters.cliente.ClienteDto;
import br.com.estudos.crud.repository.ClienteRepository;
import br.com.estudos.crud.service.CadastroClienteService;
import br.com.estudos.crud.utils.queries.mappers.ClienteMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class CadastroClienteServiceImpl implements CadastroClienteService {

    private final ClienteRepository clienteRepository;

    public Long cadastrar(final ClienteRequest request) {
        return clienteRepository.insertClient(request);
    }

    @Override
    public ClienteDto findByCpfDto(final String cpf) {
        final var cliente = clienteRepository.findByCpf(cpf);
        return ClienteMapper.mapCliente(cliente);
    }

    @Override
    public Cliente findByCpf(final String cpf) {
        return clienteRepository.findByCpf(cpf);
    }

    @Override
    public List<Cliente> findAll() {
        return clienteRepository.findAll();
    }

    @Override
    public String findCpf(final String cpf) {
        return clienteRepository.findCpf(cpf);
    }

    public int deleteClienteByCpf(final String cpf) {
        return clienteRepository.deleteClientByCpf(cpf);
    }

}
