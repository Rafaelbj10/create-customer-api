package com.create.customer.utils.queries.mappers;

import com.create.customer.domain.model.Client;
import com.create.customer.domain.parameters.ClientRequest;
import com.create.customer.infrastructure.client.ClientDto;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Mapper for Client entity and DTOs
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class ClientMapper {

    public static MapSqlParameterSource mapParameters(final ClientRequest client) {
        return new MapSqlParameterSource()
                .addValue("NAME", client.getName())
                .addValue("CPF", client.getCpf())
                .addValue("RG", client.getRg())
                .addValue("ADDRESS", client.getAddress())
                .addValue("ZIP_CODE", client.getZipCode())
                .addValue("EMAIL", client.getEmail())
                .addValue("TELEPHONE", client.getTelephone())
                .addValue("DESCRIPTION", client.getDescription())
                .addValue("BIRTH_DATE", client.getBirthDate());
    }

    public static ClientDto mapToUpdateParameters(final ClientRequest client) {
        return ClientDto.builder()
                .name(client.getName())
                .cpf(client.getCpf())
                .rg(client.getRg())
                .address(client.getAddress())
                .zipCode(client.getZipCode())
                .email(client.getEmail())
                .telephone(client.getTelephone())
                .description(client.getDescription())
                .birthDate(client.getBirthDate())
                .build();
    }

    public static List<ClientDto> map(final List<Client> clients) {
        return clients.stream()
                .map(ClientMapper::mapClient)
                .collect(Collectors.toList());
    }

    public static ClientDto mapClient(final Client client) {
        return ClientDto.builder()
                .name(client.getName())
                .cpf(client.getCpf())
                .rg(client.getRg())
                .address(client.getAddress())
                .zipCode(client.getZipCode())
                .email(client.getEmail())
                .telephone(client.getTelephone())
                .description(client.getDescription())
                .birthDate(client.getBirthDate())
                .build();
    }


}
