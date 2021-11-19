package br.com.estudos.crud.controller.Impl;

import br.com.estudos.crud.controller.CadastroClienteController;
import br.com.estudos.crud.dto.ClienteDto;
import br.com.estudos.crud.model.Cliente;
import br.com.estudos.crud.service.Impl.CadastroClienteServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class CadastroClienteControllerImpl implements CadastroClienteController {

    private final CadastroClienteServiceImpl cadastroClienteServiceImpl;

    public ResponseEntity<ClienteDto> cadastrar(final Cliente cliente) {
        cadastroClienteServiceImpl.cadastrar(cliente);
        return new ResponseEntity(HttpStatus.CREATED);
    }

}
