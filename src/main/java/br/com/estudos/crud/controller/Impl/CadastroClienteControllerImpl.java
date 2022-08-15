package br.com.estudos.crud.controller.Impl;

import br.com.estudos.crud.business.CadastroClienteBusiness;
import br.com.estudos.crud.controller.CadastroClienteController;
import br.com.estudos.crud.parameters.ClienteRequest;
import br.com.estudos.crud.presenters.cliente.ClienteDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class CadastroClienteControllerImpl implements CadastroClienteController {
    private final CadastroClienteBusiness cadastroClienteBusiness;

    public ResponseEntity<Void> cadastrar(final ClienteRequest request) {
        cadastroClienteBusiness.cadastrar(request);
        return new ResponseEntity(HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<ClienteDto> findByCpf(final String cpf) {
        final var response = cadastroClienteBusiness.findByCpf(cpf);
        return ResponseEntity.ok(response);
    }

    @Override
    public List<ClienteDto> findAll() {
        return cadastroClienteBusiness.findAll();
    }

    @Override
    public ResponseEntity<Void> deleteClienteByCpf(final String cpf) {
        cadastroClienteBusiness.deleteClienteByCpf(cpf);
        return new ResponseEntity(HttpStatus.NON_AUTHORITATIVE_INFORMATION);
    }

}
