package br.com.estudos.crud.presenters.client;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

/**
 * Data Transfer Object for client information
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ClientDto {

    @Schema(description = "Client full name")
    private String name;
    @Schema(description = "Client email address")
    private String email;
    @Schema(description = "Client telephone number")
    private String telephone;
    @Schema(description = "Client description")
    private String description;
}
