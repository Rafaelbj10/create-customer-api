package br.com.estudos.crud.mapper;

import br.com.estudos.crud.dto.ClienteDto;
import br.com.estudos.crud.model.Cliente;

public class ClienteMapper2 {

    public static ClienteDto map(Cliente cliente) {
        return ClienteDto.builder()
                .id(cliente.getId())
                .endereco(cliente.getEndereco())
                .nome(cliente.getNome())
                .build();
    }
}
