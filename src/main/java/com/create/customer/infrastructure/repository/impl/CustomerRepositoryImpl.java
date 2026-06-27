package com.create.customer.infrastructure.repository.impl;

import com.create.customer.domain.model.Customer;
import com.create.customer.domain.parameters.ClientRequest;
import com.create.customer.infrastructure.client.ClientDto;
import com.create.customer.infrastructure.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbEnhancedClient;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbTable;
import software.amazon.awssdk.enhanced.dynamodb.Key;
import software.amazon.awssdk.enhanced.dynamodb.TableSchema;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Slf4j
@Repository
@RequiredArgsConstructor
public class CustomerRepositoryImpl implements CustomerRepository {

    private final DynamoDbEnhancedClient enhancedClient;

    private DynamoDbTable<Customer> table() {
        return enhancedClient.table("TB_CLIENT", TableSchema.fromBean(Customer.class));
    }

    @Override
    public UUID insertClient(ClientRequest request, UUID externalId) {
        Customer customer = Customer.builder()
                .cpf(request.getCpf())
                .name(request.getName())
                .rg(request.getRg())
                .address(request.getAddress())
                .zipCode(request.getZipCode())
                .email(request.getEmail())
                .telephone(request.getTelephone())
                .description(request.getDescription())
                .birthDate(request.getBirthDate() != null ? request.getBirthDate().toString() : null)
                .createdAt(LocalDateTime.now().toString())
                .updatedAt(LocalDateTime.now().toString())
                .build();

        table().putItem(customer);
        log.info("Client inserted successfully with externalId: {}", externalId);
        return externalId;
    }

    @Override
    public Customer findByCpf(String cpf) {
        Key key = Key.builder().partitionValue(cpf).build();
        return table().getItem(key);
    }

    @Override
    public String findCpf(String cpf) {
        Customer customer = findByCpf(cpf);
        return customer != null ? customer.getCpf() : null;
    }

    @Override
    public List<ClientDto> findAll() {
        return table().scan().items().stream()
                .map(this::toDto)
                .toList();
    }

    @Override
    public int deleteClientByCpf(String cpf) {
        Key key = Key.builder().partitionValue(cpf).build();
        Customer deleted = table().deleteItem(key);
        return deleted != null ? 1 : 0;
    }

    private ClientDto toDto(Customer customer) {
        return ClientDto.builder()
                .name(customer.getName())
                .email(customer.getEmail())
                .telephone(customer.getTelephone())
                .description(customer.getDescription())
                .cpf(customer.getCpf())
                .rg(customer.getRg())
                .zipCode(customer.getZipCode())
                .address(customer.getAddress())
                .birthDate(customer.getBirthDate() != null
                        ? java.time.LocalDate.parse(customer.getBirthDate())
                        : null)
                .build();
    }
}