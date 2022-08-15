package br.com.estudos.crud.parameters;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ClienteRequest {

    @ApiModelProperty(value = "Nome do cliente")
    private String name;
    @ApiModelProperty(value = "CPF do cliente")
    private String cpf;
    @ApiModelProperty(value = "RG do cliente")
    private String rg;
    @ApiModelProperty(value = "Endereco do cliente")
    private String address;
    @ApiModelProperty(value = "Endereco do cliente do cliente")
    private String email;
    @ApiModelProperty(value = "E-mail do cliente")
    private String telephone;
    @ApiModelProperty(value = "Descricao")
    private String description;

}
