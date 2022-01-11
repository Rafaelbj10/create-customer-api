package br.com.estudos.crud.controller.Impl;

import br.com.estudos.crud.controller.CadastroClienteController;
import br.com.estudos.crud.dto.ClienteDto;
import br.com.estudos.crud.model.Cliente;
import br.com.estudos.crud.service.CadastroClienteService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class CadastroClienteControllerImpl implements CadastroClienteController {

    private final CadastroClienteService cadastroClienteService;

    public ResponseEntity<ClienteDto> cadastrar(final Cliente cliente) {
        cadastroClienteService.cadastrar(cliente);
        return new ResponseEntity(HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<Cliente> buscar(String cpf) {
        cadastroClienteService.buscar(cpf);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }
}
