package br.com.estudos.crud.presenters.client.viacep;

import lombok.*;

/**
 * Response model from ViaCEP API
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ViaCepResponse {

    private String street;
    private String complement;
    private String neighborhood;
    private String city;
    private String state;

}

