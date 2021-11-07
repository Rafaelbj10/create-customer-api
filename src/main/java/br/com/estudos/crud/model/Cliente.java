package br.com.estudos.crud.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

@NoArgsConstructor
@Data
@Entity
public class Cliente {

    @ApiModelProperty(value = "Código do cliente")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ApiModelProperty(value = "Nome do cliente")
    private String nome;
    @ApiModelProperty(value = "CPF do cliente")
    private String cpf;
    @ApiModelProperty(value = "Endereço do cliente")
    private String endereco;
    @ApiModelProperty(value = "Data de nascimento do cliente")
    private Date dataNascimento;

    public Cliente(Long id, String nome, String endereco) {
        this.id = id;
        this.nome = nome;
        this.endereco = endereco;
        this.dataNascimento = dataNascimento;
    }


}
