package br.com.estudos.crud.parameters;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class ClienteRequest {

    @Schema(description = "Nome do cliente")
    private String name;
    @Schema(description = "CPF do cliente")
    private String cpf;
    @Schema(description = "RG do cliente")
    private String rg;
    @Schema(description = "Endereco do cliente")
    @JsonIgnore
    private String address;
    @Schema(description = "Cep do cliente")
    private String cep;
    @Schema(description = "Endereco de e-mail do cliente")
    private String email;
    @Schema(description = "Telefone do cliente")
    private String telephone;
    @Schema(description = "Descricao")
    private String description;
    @Schema(description = "Data nascimento")
    private String birthDate;
}
