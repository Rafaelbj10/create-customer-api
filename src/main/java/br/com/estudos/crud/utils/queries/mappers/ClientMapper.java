package br.com.estudos.crud.utils.queries.mappers;

import br.com.estudos.crud.model.Client;
import br.com.estudos.crud.parameters.ClientRequest;
import br.com.estudos.crud.presenters.client.ClientDto;
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
                .email(client.getEmail())
                .telephone(client.getTelephone())
                .description(client.getDescription())
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
                .email(client.getEmail())
                .telephone(client.getTelephone())
                .description(client.getDescription())
                .build();
    }


}
