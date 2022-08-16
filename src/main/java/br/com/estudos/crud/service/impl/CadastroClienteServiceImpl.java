package br.com.estudos.crud.service.impl;

import br.com.estudos.crud.parameters.ClienteRequest;
import br.com.estudos.crud.presenters.cliente.ClienteDto;
import br.com.estudos.crud.repository.ClienteRepository;
import br.com.estudos.crud.service.CadastroClienteService;
import br.com.estudos.crud.service.ViaCepService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class CadastroClienteServiceImpl implements CadastroClienteService {

    private final ClienteRepository clienteRepository;
    private final ViaCepService viaCepService;

    public Long cadastrar(final ClienteRequest request) {
        final var addressClient = viaCepService.getCep(request.getCep());
        request.setAddress(addressClient.toString());
        return clienteRepository.insertClient(request);
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
