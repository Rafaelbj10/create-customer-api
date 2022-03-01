package br.com.estudos.crud.presenters.cliente;

import io.swagger.annotations.ApiModelProperty;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ClienteDto {

    @ApiModelProperty(value = "Id do cliente", example = "1")
    private Long id;
    @ApiModelProperty(value = "Nome do cliente", example = "Rafael Batista de Oliveira")
    private String nome;
    @ApiModelProperty(value = "Endereco do cliente", example = "Avenida José Barbosa de Siqueira 1805")
    private String endereco;
}
