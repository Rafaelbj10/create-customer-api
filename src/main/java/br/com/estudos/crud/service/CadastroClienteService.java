package br.com.estudos.crud.service;

import br.com.estudos.crud.model.Cliente;
import br.com.estudos.crud.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class CadastroClienteService {

    @Autowired
    ClienteRepository clienteRepository;

    public List<Cliente> listar() {
        return clienteRepository.findAll();
    }

    public Optional<Cliente> buscar(@PathVariable Long id) {
        return clienteRepository.findById(id);
    }

    public Cliente cadastrar(@RequestBody Cliente cliente) {
        clienteRepository.save(cliente);
        return cliente;
    }

    public void alterar(@RequestBody Cliente imput, @PathVariable Long id) {
        Optional<Cliente> usuario = clienteRepository.findById(id);
        if (usuario.isPresent()) {
            Cliente entity = usuario.get();

            entity.setId(imput.getId());
            entity.setNome(imput.getNome());
            entity.setEndereco(imput.getEndereco());
            entity.setCpf(imput.getCpf());
            entity.setDataNascimento(imput.getDataNascimento());

            clienteRepository.save(entity);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuario não existe no banco");
        }

    }

    public void deletar(@PathVariable Long id){
        clienteRepository.deleteById(id);
    }

}
