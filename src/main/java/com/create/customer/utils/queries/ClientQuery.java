package com.create.customer.utils.queries;

import lombok.NoArgsConstructor;

import static lombok.AccessLevel.PRIVATE;

/**
 * SQL queries for client database operations
 */
@NoArgsConstructor(access = PRIVATE)
public class ClientQuery {

    public static final String INSERT_CLIENT = "INSERT INTO appdb.TB_CLIENT " +
            "(NAME, CPF, RG, ADDRESS, ZIP_CODE, EMAIL, TELEPHONE, DESCRIPTION, BIRTH_DATE ) " +
            "VALUES " +
            "(:NAME, :CPF, :RG, :ADDRESS, :ZIP_CODE, :EMAIL, :TELEPHONE, :DESCRIPTION, :BIRTH_DATE)";

    public static final String FIND_BY_CPF = "SELECT C.* FROM appdb.TB_CLIENT C WHERE CPF = ? ";

    public static final String FIND_CPF = "SELECT C.CPF FROM appdb.TB_CLIENT C WHERE CPF = ? ";

    public static final String FIND_ALL_CLIENT = "SELECT TC.* FROM appdb.TB_CLIENT TC ";

    public static final String DELETE_CLIENT_BY_CPF = "DELETE FROM appdb.TB_CLIENT WHERE CPF = ? ";

}
