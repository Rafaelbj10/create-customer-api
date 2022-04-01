package br.com.estudos.crud.controller.Impl;

import br.com.estudos.crud.controller.CadastroClienteController;
import br.com.estudos.crud.parameters.ClienteRequest;
import br.com.estudos.crud.presenters.cliente.ClienteDto;
import br.com.estudos.crud.service.CadastroClienteService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class CadastroClienteControllerImpl implements CadastroClienteController {

    private final CadastroClienteService cadastroClienteService;

    public ResponseEntity<Void> cadastrar(final ClienteRequest request) {
        cadastroClienteService.cadastrar(request);
        return new ResponseEntity(HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<ClienteDto> findByCpf(final String cpf) {
        final var response = cadastroClienteService.findByCpf(cpf);
        return ResponseEntity.ok(response);
    }

    @Override
    public List<ClienteDto> findAll() {
        return cadastroClienteService.findAll();
    }

    @Override
    public ResponseEntity<Void> deleteClienteByCpf(final String cpf) {
        cadastroClienteService.deleteClienteByCpf(cpf);
        return new ResponseEntity(HttpStatus.NON_AUTHORITATIVE_INFORMATION);
    }

}
