package br.com.estudos.crud.utils.queries.mappers;

import br.com.estudos.crud.model.Cliente;
import br.com.estudos.crud.parameters.ClienteRequest;
import br.com.estudos.crud.presenters.cliente.ClienteDto;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;

import java.util.List;
import java.util.stream.Collectors;


@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class ClienteMapper {

    public static MapSqlParameterSource mapParameters(final ClienteRequest cliente) {
        return new MapSqlParameterSource()
                .addValue("NAME", cliente.getName())
                .addValue("CPF", cliente.getCpf())
                .addValue("RG", cliente.getRg())
                .addValue("ADDRESS", cliente.getAddress())
                .addValue("CEP", cliente.getCep())
                .addValue("EMAIL", cliente.getEmail())
                .addValue("TELEPHONE", cliente.getTelephone())
                .addValue("DESCRIPTION", cliente.getDescription())
                .addValue("BIRTH_DATE", cliente.getBirthDate());
    }

    public static ClienteDto mapToUpdateParameters(final ClienteRequest cliente) {
        return ClienteDto.builder()
                .name(cliente.getName())
                .email(cliente.getEmail())
                .telephone(cliente.getTelephone())
                .description(cliente.getDescription())
                .build();
    }

    public static List<ClienteDto> map(final List<Cliente> clientes) {

        return clientes.stream()
                .map(ClienteMapper::mapCliente)
                .collect(Collectors.toList());

    }

    public static ClienteDto mapCliente(final Cliente cliente) {
        return ClienteDto.builder()
                .name(cliente.getName())
                .email(cliente.getEmail())
                .telephone(cliente.getTelephone())
                .description(cliente.getDescription())
                .build();
    }



}
