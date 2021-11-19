package br.com.estudos.crud.mapper;

import br.com.estudos.crud.dto.ClienteDto;
import br.com.estudos.crud.model.Cliente;

import java.util.ArrayList;
import java.util.List;

public class ClienteMapper {

    public List<ClienteDto> map(List<Cliente> clienteList) {

        List<ClienteDto> clienteDto = new ArrayList<>();

        for (Cliente cliente : clienteList) {

            ClienteDto dto = new ClienteDto();

            dto.setId(cliente.getId());
            dto.setNome(cliente.getNome());
            dto.setEndereco(cliente.getEndereco());

            clienteDto.add(dto);

        }

        return clienteDto;

    }

}
