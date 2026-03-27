package br.com.estudos.crud.controller;

import br.com.estudos.crud.parameters.ClientRequest;
import br.com.estudos.crud.presenters.client.ClientDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Clients", description = "Client management endpoints")
@RequestMapping(value = "/client", produces = "application/json")
public interface ClientRegistrationController {

    @Operation(
            summary = "Register a new client",
            description = "Endpoint responsible for registering a new user"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "User successfully registered!"),
            @ApiResponse(responseCode = "400", description = "Invalid request data"),
            @ApiResponse(responseCode = "403", description = "You do not have permission to access this resource"),
            @ApiResponse(responseCode = "500", description = "An exception occurred")
    })
    @PostMapping("/register")
    ResponseEntity<Void> register(@RequestBody ClientRequest request);

    @Operation(
            summary = "Find client by CPF",
            description = "Endpoint responsible for finding a user by CPF"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Returns the requested user"),
            @ApiResponse(responseCode = "400", description = "Invalid CPF format"),
            @ApiResponse(responseCode = "403", description = "You do not have permission to access this resource"),
            @ApiResponse(responseCode = "404", description = "Client not found"),
            @ApiResponse(responseCode = "500", description = "An exception occurred")
    })
    @GetMapping("/{cpf}")
    ResponseEntity<ClientDto> findByCpf(@PathVariable String cpf);

    @Operation(
            summary = "Find all clients",
            description = "Endpoint responsible for finding all users"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Returns all users"),
            @ApiResponse(responseCode = "403", description = "You do not have permission to access this resource"),
            @ApiResponse(responseCode = "500", description = "An exception occurred")
    })
    @GetMapping
    List<ClientDto> findAll();

    @Operation(
            summary = "Delete user by CPF",
            description = "Endpoint responsible for deleting a user by CPF"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Client successfully deleted"),
            @ApiResponse(responseCode = "400", description = "Invalid CPF format"),
            @ApiResponse(responseCode = "403", description = "You do not have permission to access this resource"),
            @ApiResponse(responseCode = "404", description = "Client not found"),
            @ApiResponse(responseCode = "500", description = "An exception occurred")
    })
    @DeleteMapping("/{cpf}")
    ResponseEntity<Void> deleteByCpf(@PathVariable String cpf);
}
