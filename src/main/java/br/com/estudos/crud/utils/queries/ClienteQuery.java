package br.com.estudos.crud.utils.queries;

import lombok.NoArgsConstructor;

import static lombok.AccessLevel.PRIVATE;

@NoArgsConstructor(access = PRIVATE)
public class ClienteQuery {

    public static final String INSERT_CLIENT = "INSERT INTO digibank.TB_CLIENTE " +
            "(NAME, CPF, RG, ADDRESS, CEP, EMAIL, TELEPHONE, DESCRIPTION, BIRTH_DATE ) " +
            "VALUES " +
            "(:NAME, :CPF, :RG, :ADDRESS, :CEP, :EMAIL, :TELEPHONE, :DESCRIPTION, :BIRTH_DATE)";

    public static final String FIND_BY_CPF = "SELECT C.* FROM digibank.TB_CLIENTE C WHERE CPF = ? ";

    public static final String FIND_CPF = "SELECT C.CPF FROM digibank.TB_CLIENTE C WHERE CPF = ? ";

    public static final String FIND_ALL_CLIENT = "SELECT TC.* FROM digibank.TB_CLIENTE TC ";

    public static final String DELETE_CLIENT_BY_CPF = "DELETE FROM digibank.TB_CLIENTE WHERE CPF = ? ";


}
