package com.create.customer.presentation.controller;

import com.create.customer.domain.parameters.ClientRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

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

}

