package br.com.estudos.crud.utils.queries;

import lombok.NoArgsConstructor;

import static lombok.AccessLevel.PRIVATE;

@NoArgsConstructor(access = PRIVATE)
public class ClienteQuery {


    public static final String INSERT_CLIENT = "INSERT INTO cliente (" +
            "nome," +
            "cpf," +
            "endereco)" +
            "VALUES (" +
            "?," +
            "?," +
            "?)";

    public static final String BUSCAR_POR_ID =
            "SELECT c.* FROM armazem.cliente c WHERE CPF = ? ";

    public static final String FIND_ALL_CLIENT = "SELECT * FROM armazem.cliente ";

    public static final String DELETE_CLIENT_BY_ID = "DELETE FROM armazem.cliente WHERE CPF = ? ";


}
