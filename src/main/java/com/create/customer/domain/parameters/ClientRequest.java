package com.create.customer.domain.parameters;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * Request DTO for client registration
 */
@Data
public class ClientRequest {

    @Schema(description = "Client full name", example = "John Doe")
    private String name;

    @Schema(description = "Client CPF (Brazilian Tax ID)", example = "12345678901")
    private String cpf;

    @Schema(description = "Client RG (Brazilian Identity Document)", example = "123456789")
    private String rg;

    @Schema(description = "Client street address")
    @JsonIgnore
    private String address;

    @Schema(description = "Client postal/ZIP code", example = "12345678")
    private String zipCode;

    @Schema(description = "Client email address", example = "client@example.com")
    private String email;

    @Schema(description = "Client telephone number", example = "11999999999")
    private String telephone;

    @Schema(description = "Client description/notes")
    private String description;

    @Schema(description = "Client date of birth (ISO format: YYYY-MM-DD)", example = "1990-01-15")
    private LocalDate birthDate;

    @Schema(description = "Client monthly income", example = "5000.00")
    private BigDecimal monthlyIncome;
}
