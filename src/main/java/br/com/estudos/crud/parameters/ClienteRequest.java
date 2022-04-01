package br.com.estudos.crud.parameters;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Id;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ClienteRequest {

    @ApiModelProperty(value = "Código do cliente")
    @Id
    @JsonIgnore
    private Long id;
    @ApiModelProperty(value = "Nome do cliente")
    private String name;
    @ApiModelProperty(value = "CPF do cliente")
    private String cpf;
    @ApiModelProperty(value = "Endereço do cliente")
    private String endereco;

}
