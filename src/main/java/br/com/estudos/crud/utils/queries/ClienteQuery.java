package br.com.estudos.crud.utils.queries;

import lombok.NoArgsConstructor;

import static lombok.AccessLevel.PRIVATE;

@NoArgsConstructor(access = PRIVATE)
public class ClienteQuery {


    public static final String INSERT_CLIENT = "INSERT INTO rafaelbj.tb_cliente (" +
            "name," +
            "cpf," +
            "endereco)" +
            "VALUES (" +
            "?," +
            "?," +
            "?)";

    public static final String BUSCAR_POR_ID =
            "SELECT c.* FROM rafaelbj.tb_cliente c WHERE CPF = ? ";

    public static final String FIND_ALL_CLIENT = "SELECT * FROM rafaelbj.tb_cliente ";

    public static final String DELETE_CLIENT_BY_ID = "DELETE FROM rafaelbj.tb_cliente WHERE CPF = ? ";


}
