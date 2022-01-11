package br.com.estudos.crud.controller;

import br.com.estudos.crud.dto.ClienteDto;
import br.com.estudos.crud.model.Cliente;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Api(tags = "clientes")
@RequestMapping(value = "/cadastro-cliente", produces = "application/json")
public interface CadastroClienteController {

    @ApiOperation(value = "Cadastra cliente")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Retorna o cliente cadastrado"),
            @ApiResponse(code = 403, message = "Você não tem permissão para acessar este recurso"),
            @ApiResponse(code = 500, message = "Foi gerada uma exceção"),
    })
    @PostMapping
    ResponseEntity<ClienteDto> cadastrar(@RequestBody Cliente cliente);


    @ApiOperation(value = "Buscar cliente por CPF")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Retorna o cliente solicitado"),
            @ApiResponse(code = 403, message = "Você não tem permissão para acessar este recurso"),
            @ApiResponse(code = 500, message = "Foi gerada uma exceção"),
    })
    @GetMapping(value = "/{cpf}", produces = "application/json")
    ResponseEntity<Cliente> buscar(@PathVariable String cpf);


}
