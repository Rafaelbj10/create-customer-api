package br.com.estudos.crud.controller;

import br.com.estudos.crud.parameters.ClienteRequest;
import br.com.estudos.crud.presenters.cliente.ClienteDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(tags = "clientes")
@RequestMapping(value = "/cliente", produces = "application/json")
public interface CadastroClienteController {

    @ApiOperation(value = "Cadastra cliente", notes = "Endpoint responsável por cadastrar usuário")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Usuário cadastrado com sucesso!"),
            @ApiResponse(code = 403, message = "Você não tem permissão para acessar este recurso"),
            @ApiResponse(code = 500, message = "Foi gerada uma exceção"),
    })
    @PostMapping
    ResponseEntity<Void> cadastrar(@RequestBody ClienteRequest request);

    @ApiOperation(value = "Buscar cliente por CPF", notes = "Endpoint responsável por buscar usuário por CPF!!")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Retorna o usuário solicitado"),
            @ApiResponse(code = 403, message = "Você não tem permissão para acessar este recurso"),
            @ApiResponse(code = 500, message = "Foi gerada uma exceção"),
    })
    @GetMapping(value = "/{cpf}", produces = "application/json")
    ResponseEntity<ClienteDto> findByCpf(@PathVariable String cpf);

    @ApiOperation(value = "Buscar todos os cliente", notes = "Endpoint resposável por buscar todos os usuários!")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Retorna todos os usuários"),
            @ApiResponse(code = 403, message = "Você não tem permissão para acessar este recurso"),
            @ApiResponse(code = 500, message = "Foi gerada uma exceção"),
    })
    @GetMapping(produces = "application/json")
    List<ClienteDto> findAll();

    @ApiOperation(value = "Deletar usuário por cpf", notes = "Endpoint responsável por deletar usuário por CPF")
    @ApiResponses(value = {
            @ApiResponse(code = 203, message = "Cliente deletado com sucesso"),
            @ApiResponse(code = 403, message = "Você não tem permissão para acessar este recurso"),
            @ApiResponse(code = 500, message = "Foi gerada uma exceção"),
    })
    @DeleteMapping(value = "/{cpf}", produces = "application/json")
    ResponseEntity<Void> deleteClienteByCpf(@PathVariable String cpf);

}
