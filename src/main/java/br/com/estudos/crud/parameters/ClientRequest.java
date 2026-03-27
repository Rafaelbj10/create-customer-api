package br.com.estudos.crud.parameters;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * Request DTO for client registration
 */
@Data
public class ClientRequest {

    @Schema(description = "Client full name")
    private String name;
    @Schema(description = "Client CPF")
    private String cpf;
    @Schema(description = "Client RG")
    private String rg;
    @Schema(description = "Client address")
    @JsonIgnore
    private String address;
    @Schema(description = "Client ZIP code")
    private String zipCode;
    @Schema(description = "Client email address")
    private String email;
    @Schema(description = "Client telephone number")
    private String telephone;
    @Schema(description = "Client description")
    private String description;
    @Schema(description = "Client birth date")
    private String birthDate;
}
