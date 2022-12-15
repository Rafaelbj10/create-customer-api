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
    @ApiModelProperty(value = "Endereco do cliente")
    private String address;
    @ApiModelProperty(value = "Endereco de e-mail do cliente")
    private String email;
    @ApiModelProperty(value = "Telefone do cliente")
    private String telephone;
    @ApiModelProperty(value = "Descricao")
    private String description;
}
