package br.com.estudos.crud.repository.impl;

import br.com.estudos.crud.exception.UnprocessableEntityException;
import br.com.estudos.crud.model.Cliente;
import br.com.estudos.crud.parameters.ClienteRequest;
import br.com.estudos.crud.presenters.cliente.ClienteDto;
import br.com.estudos.crud.repository.ClienteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataAccessException;
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
            throw new UnprocessableEntityException("Erro ao recuperar o id da solicitação!");
        } catch (final DataAccessException e) {
            throw new UnprocessableEntityException("Erro ao inserir registro na base!");
        }
    }

    @Override
    public Cliente findByCpf(final String cpf) {
        try {
            return jdbcTemplate.queryForObject(FIND_BY_CPF, new BeanPropertyRowMapper<>(Cliente.class), cpf);
        } catch (final DataAccessException e) {
            throw new UnprocessableEntityException("Não foi possível encontrar o cliente na base.");
        }
    }

    public List<ClienteDto> findAll() {
        try {
            return jdbcTemplate.query(FIND_ALL_CLIENT, new BeanPropertyRowMapper<>(ClienteDto.class));
        } catch (final DataAccessException e) {
            throw new UnprocessableEntityException("Não foi possível buscar todos os clientes no banco de dados. ");
        }
    }

    public int deleteClientByCpf(final String cpf) {
        try {
            return jdbcTemplate.update(DELETE_CLIENT_BY_CPF, cpf);
        } catch (final DataAccessException e) {
            throw new UnprocessableEntityException("Erro ao deletar o cliente!");
        }
    }

}



