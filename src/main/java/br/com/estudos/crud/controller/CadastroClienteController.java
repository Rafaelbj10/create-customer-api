package br.com.estudos.crud.controller;

import br.com.estudos.crud.parameters.ClienteRequest;
import br.com.estudos.crud.presenters.cliente.ClienteDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Clientes", description = "Endpoints de gerenciamento de clientes")
@RequestMapping(value = "/cliente", produces = "application/json")
public interface CadastroClienteController {

    @Operation(
            summary = "Cadastra cliente",
            description = "Endpoint responsável por cadastrar usuário"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Usuário cadastrado com sucesso!"),
            @ApiResponse(responseCode = "403", description = "Você não tem permissão para acessar este recurso"),
            @ApiResponse(responseCode = "500", description = "Foi gerada uma exceção")
    })
    @PostMapping("/cadastrar")
    ResponseEntity<Void> cadastrar(@RequestBody ClienteRequest request);

    @Operation(
            summary = "Buscar cliente por CPF",
            description = "Endpoint responsável por buscar usuário por CPF"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Retorna o usuário solicitado"),
            @ApiResponse(responseCode = "403", description = "Você não tem permissão para acessar este recurso"),
            @ApiResponse(responseCode = "500", description = "Foi gerada uma exceção")
    })
    @GetMapping("/{cpf}")
    ResponseEntity<ClienteDto> findByCpf(@PathVariable String cpf);

    @Operation(
            summary = "Buscar todos os clientes",
            description = "Endpoint responsável por buscar todos os usuários"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Retorna todos os usuários"),
            @ApiResponse(responseCode = "403", description = "Você não tem permissão para acessar este recurso"),
            @ApiResponse(responseCode = "500", description = "Foi gerada uma exceção")
    })
    @GetMapping
    List<ClienteDto> findAll();

    @Operation(
            summary = "Deletar usuário por CPF",
            description = "Endpoint responsável por deletar usuário por CPF"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "203", description = "Cliente deletado com sucesso"),
            @ApiResponse(responseCode = "403", description = "Você não tem permissão para acessar este recurso"),
            @ApiResponse(responseCode = "500", description = "Foi gerada uma exceção")
    })
    @DeleteMapping("/{cpf}")
    ResponseEntity<Void> deleteByCpf(@PathVariable String cpf);
}
