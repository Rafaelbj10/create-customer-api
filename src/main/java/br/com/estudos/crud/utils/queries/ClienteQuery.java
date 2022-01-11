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
            "SELECT * FROM armazem.cliente WHERE CPF = " +
                    "? ";


}
