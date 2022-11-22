package br.com.estudos.crud.presenters.cliente.viacep;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ViaCepResponse {

    private String logradouro;
    private String complemento;
    private String bairro;
    private String localidade;
    private String uf;

}
