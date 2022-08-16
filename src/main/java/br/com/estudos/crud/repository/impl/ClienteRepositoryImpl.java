package br.com.estudos.crud.repository.impl;

import br.com.estudos.crud.parameters.ClienteRequest;
import br.com.estudos.crud.presenters.cliente.ClienteDto;
import br.com.estudos.crud.repository.ClienteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.util.List;

import static br.com.estudos.crud.utils.queries.ClienteQuery.*;
import static br.com.estudos.crud.utils.queries.mappers.ClienteMapper.mapParameters;
import static java.util.Objects.isNull;

@RequiredArgsConstructor
@Repository
public class ClienteRepositoryImpl implements ClienteRepository {

    private final JdbcTemplate jdbcTemplate;

    private final NamedParameterJdbcOperations namedParameterJdbcOperations;

    public Long insertClient(final ClienteRequest request) {

        KeyHolder keyHolder = new GeneratedKeyHolder();
        try {
            namedParameterJdbcOperations.update(INSERT_CLIENT, mapParameters(request), keyHolder, new String[]{"ID"});
            final var key = keyHolder.getKey();
            if (!isNull(key)) {
                return key.longValue();
            }
            throw new RuntimeException();
        } catch (DataAccessException e) {
            throw new RuntimeException();
        }
    }

    @Override
    public ClienteDto findByCpf(final String cpf) {
        try {
            return jdbcTemplate.queryForObject(BUSCAR_POR_ID, new BeanPropertyRowMapper<>(ClienteDto.class), cpf);
        } catch (EmptyResultDataAccessException e) {
            throw new RuntimeException("Não foi possível encontrar o cliente na base.");
        }
    }

    public List<ClienteDto> findAll() {
        try {
            return jdbcTemplate.query(FIND_ALL_CLIENT, new BeanPropertyRowMapper<>(ClienteDto.class));
        } catch (EmptyResultDataAccessException e) {
            throw new RuntimeException("Não foi possível buscar todos os clientes no banco de dados. ");
        }
    }

    public void deleteClientByCpf(final String cpf) {
        try {
            jdbcTemplate.update(DELETE_CLIENT_BY_ID, cpf);
        } catch (EmptyResultDataAccessException e) {
            throw new RuntimeException("Não foi possível deletar cliente");
        }
    }

}



