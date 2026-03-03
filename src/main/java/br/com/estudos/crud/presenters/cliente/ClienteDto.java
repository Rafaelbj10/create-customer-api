package br.com.estudos.crud.presenters.cliente;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ClienteDto {

    @Schema(description = "Nome do cliente")
    private String name;
    @Schema(description = "Endereco de e-mail do cliente")
    private String email;
    @Schema(description = "Telefone do cliente")
    private String telephone;
    @Schema(description = "Descricao")
    private String description;
}
