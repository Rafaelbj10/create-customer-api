package br.com.estudos.crud.repository.Impl;

import br.com.estudos.crud.model.Cliente;
import br.com.estudos.crud.repository.ClienteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import static br.com.estudos.crud.utils.queries.ClienteQuery.BUSCAR_POR_ID;
import static br.com.estudos.crud.utils.queries.ClienteQuery.INSERT_CLIENT;

@RequiredArgsConstructor
@Repository
public class ClienteRepositoryImpl implements ClienteRepository {

    private final JdbcTemplate jdbcTemplate;

    public void insertClient(final Cliente cliente) {
        jdbcTemplate.update(INSERT_CLIENT, cliente.getNome(), cliente.getCpf(), cliente.getEndereco());
    }

    @Override
    public Cliente buscar(final String cpf) {
        try {
            jdbcTemplate.queryForObject(BUSCAR_POR_ID, Cliente.class, cpf);
        } catch (DataAccessException d) {
            throw new DataAccessException("Erro ao buscar cliente") {
            };
        }
        return null;
    }


}
