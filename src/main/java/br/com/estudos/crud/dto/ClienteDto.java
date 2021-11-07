package br.com.estudos.crud.dto;

import br.com.estudos.crud.model.Cliente;
import lombok.Data;

@Data
public class ClienteDto {

    private Long id;
    private String nome;
    private String endereco;

    public Cliente transformaParaObjeto() {
        return new Cliente(id, nome, endereco);
    }


    public Long getId() {
        return id;
    }

    public ClienteDto setId(Long id) {
        this.id = id;
        return this;
    }

    public String getNome() {
        return nome;
    }

    public ClienteDto setNome(String nome) {
        this.nome = nome;
        return this;
    }

    public String getEndereco() {
        return endereco;
    }

    public ClienteDto setEndereco(String endereco) {
        this.endereco = endereco;
        return this;
    }

    @Override
    public String toString() {
        return "UsuarioDto{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", endereco='" + endereco + '\'' +
                '}';
    }
}
