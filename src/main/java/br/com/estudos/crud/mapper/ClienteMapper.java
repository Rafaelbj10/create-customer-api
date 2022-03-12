package br.com.estudos.crud.mapper;

import br.com.estudos.crud.model.Cliente;
import br.com.estudos.crud.presenters.cliente.ClienteDto;

public class ClienteMapper {


    public static ClienteDto map(final Cliente cliente) {
        return ClienteDto.builder()
                .id(cliente.getId())
                .endereco(cliente.getEndereco())
                .name(cliente.getName())
                .build();
    }

}
