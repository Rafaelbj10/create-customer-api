package br.com.estudos.crud.repository.impl;

import br.com.estudos.crud.parameters.ClienteRequest;
import br.com.estudos.crud.presenters.cliente.ClienteDto;
import br.com.estudos.crud.repository.ClienteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

import static br.com.estudos.crud.utils.queries.ClienteQuery.*;

@RequiredArgsConstructor
@Repository
public class ClienteRepositoryImpl implements ClienteRepository {

    private final JdbcTemplate jdbcTemplate;

    public void insertClient(final ClienteRequest request) {
        jdbcTemplate.update(INSERT_CLIENT, request.getName(), request.getCpf(), request.getRg(),
                request.getAddress(), request.getEmail(), request.getTelephone(), request.getDescription());
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



