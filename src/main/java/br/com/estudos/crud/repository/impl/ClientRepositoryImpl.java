package br.com.estudos.crud.repository.impl;

import br.com.estudos.crud.exception.UnprocessableEntityException;
import br.com.estudos.crud.model.Client;
import br.com.estudos.crud.parameters.ClientRequest;
import br.com.estudos.crud.presenters.client.ClientDto;
import br.com.estudos.crud.repository.ClientRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.util.List;

import static br.com.estudos.crud.utils.queries.ClientQuery.*;
import static br.com.estudos.crud.utils.queries.mappers.ClientMapper.mapParameters;
import static java.util.Objects.isNull;

/**
 * Implementation of ClientRepository
 */
@Slf4j
@RequiredArgsConstructor
@Repository
public class ClientRepositoryImpl implements ClientRepository {

    private final JdbcTemplate jdbcTemplate;
    private final NamedParameterJdbcOperations namedParameterJdbcOperations;

    @Override
    public Long insertClient(final ClientRequest request) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        try {
            namedParameterJdbcOperations.update(INSERT_CLIENT, mapParameters(request), keyHolder, new String[]{"ID"});
            final var key = keyHolder.getKey();
            if (!isNull(key)) {
                log.info("Client inserted successfully with ID: {}", key.longValue());
                return key.longValue();
            }
            log.error("Failed to retrieve generated ID from insert operation");
            throw new UnprocessableEntityException("Error retrieving the ID from the request!");
        } catch (final DataAccessException e) {
            log.error("Error inserting record into database", e);
            throw new UnprocessableEntityException("Error inserting record into database!");
        }
    }

    @Override
    public Client findByCpf(final String cpf) {
        try {
            log.debug("Searching for client with CPF: {}", cpf);
            return jdbcTemplate.queryForObject(FIND_BY_CPF, new BeanPropertyRowMapper<>(Client.class), cpf);
        } catch (final EmptyResultDataAccessException e) {
            log.warn("Client not found with CPF: {}", cpf);
            throw new UnprocessableEntityException("Client not found in database.");
        } catch (final DataAccessException e) {
            log.error("Database error while searching for client with CPF: {}", cpf, e);
            throw new UnprocessableEntityException("Could not find client in database.");
        }
    }

    @Override
    public String findCpf(final String cpf) {
        try {
            log.debug("Verifying CPF existence: {}", cpf);
            return jdbcTemplate.queryForObject(FIND_CPF, new BeanPropertyRowMapper<>(String.class), cpf);
        } catch (final EmptyResultDataAccessException e) {
            log.debug("CPF not found: {}", cpf);
            return null;
        } catch (final DataAccessException e) {
            log.error("Database error while verifying CPF: {}", cpf, e);
            throw new UnprocessableEntityException("Could not verify CPF in database.");
        }
    }

    @Override
    public List<ClientDto> findAll() {
        try {
            log.debug("Fetching all clients from database");
            return jdbcTemplate.query(FIND_ALL_CLIENT, new BeanPropertyRowMapper<>(ClientDto.class));
        } catch (final DataAccessException e) {
            log.error("Database error while fetching all clients", e);
            throw new UnprocessableEntityException("Could not fetch all clients from database.");
        }
    }

    @Override
    public int deleteClientByCpf(final String cpf) {
        try {
            log.info("Deleting client with CPF: {}", cpf);
            int result = jdbcTemplate.update(DELETE_CLIENT_BY_CPF, cpf);
            if (result == 0) {
                log.warn("No client found to delete with CPF: {}", cpf);
                throw new UnprocessableEntityException("Client not found for deletion.");
            }
            log.info("Client deleted successfully with CPF: {}", cpf);
            return result;
        } catch (final DataAccessException e) {
            log.error("Database error while deleting client with CPF: {}", cpf, e);
            throw new UnprocessableEntityException("Error deleting client!");
        }
    }

}



