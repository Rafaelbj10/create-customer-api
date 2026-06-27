package com.create.customer.utils.queries.mappers;

import com.create.customer.domain.model.Customer;
import com.create.customer.domain.parameters.ClientRequest;
import com.create.customer.infrastructure.client.ClientDto;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * Mapper for Client entity and DTOs
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class ClientMapper {

    public static Customer mapToCustomer(final ClientRequest request, UUID externalId) {
        return Customer.builder()
                .cpf(request.getCpf())
                .name(request.getName())
                .rg(request.getRg())
                .address(request.getAddress())
                .zipCode(request.getZipCode())
                .email(request.getEmail())
                .telephone(request.getTelephone())
                .description(request.getDescription())
                .birthDate(request.getBirthDate() != null ? request.getBirthDate().toString() : null)
                .build();
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

    public static List<ClientDto> map(final List<Customer> customers) {
        return customers.stream()
                .map(ClientMapper::mapClient)
                .collect(Collectors.toList());
    }

    public static ClientDto mapClient(final Customer customer) {
        return ClientDto.builder()
                .name(customer.getName())
                .cpf(customer.getCpf())
                .rg(customer.getRg())
                .address(customer.getAddress())
                .zipCode(customer.getZipCode())
                .email(customer.getEmail())
                .telephone(customer.getTelephone())
                .description(customer.getDescription())
                .birthDate(customer.getBirthDate() != null
                        ? LocalDate.parse(customer.getBirthDate())
                        : null)
                .build();
    }
}