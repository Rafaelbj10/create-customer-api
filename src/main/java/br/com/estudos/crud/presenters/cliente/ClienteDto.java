package br.com.estudos.crud.presenters.cliente;

import io.swagger.annotations.ApiModelProperty;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ClienteDto {

    @ApiModelProperty(value = "Nome do cliente")
    private String name;
    @ApiModelProperty(value = "CPF do cliente")
    private String cpf;
    @ApiModelProperty(value = "RG do cliente")
    private String rg;
    @ApiModelProperty(value = "Endereco do cliente")
    private String address;
    @ApiModelProperty(value = "Cep do cliente")
    private String cep;
    @ApiModelProperty(value = "Endereco de e-mail do cliente")
    private String email;
    @ApiModelProperty(value = "Telefone do cliente")
    private String telephone;
    @ApiModelProperty(value = "Descricao")
    private String description;
    @ApiModelProperty(value = "Data nascimento")
    private String birthDate;
}
