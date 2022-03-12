package br.com.estudos.crud.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "TB_CLIENTE", schema = "rafaelbj")
public class Cliente {

    @ApiModelProperty(value = "Código do cliente")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    private Long id;
    @ApiModelProperty(value = "Nome do cliente")
    private String name;
    @ApiModelProperty(value = "CPF do cliente")
    private String cpf;
    @ApiModelProperty(value = "Endereço do cliente")
    private String endereco;


}
