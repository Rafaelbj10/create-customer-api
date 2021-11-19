package br.com.estudos.crud.repository.Impl;

import br.com.estudos.crud.model.Cliente;
import br.com.estudos.crud.repository.ClienteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@RequiredArgsConstructor
@Repository
public class ClienteRepositoryImpl implements ClienteRepository {

    public static final String INSERT_CLIENT = "INSERT INTO cliente (" +
            "nome," +
            "cpf," +
            "endereco)" +
            "VALUES (" +
            "?," +
            "?," +
            "?)";
    private final JdbcTemplate jdbcTemplate;

    public void insertClient(final Cliente cliente) {
        jdbcTemplate.update(INSERT_CLIENT, cliente.getNome(), cliente.getCpf(), cliente.getEndereco());
    }
}
