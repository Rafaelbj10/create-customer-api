package com.create.customer.infrastructure.client.viacep;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

/**
 * Response model from ViaCEP API
 * Maps JSON response from https://viacep.com.br/
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ViaCepResponse {

    @JsonProperty("logradouro")
    private String street;

    @JsonProperty("complemento")
    private String complement;

    @JsonProperty("bairro")
    private String neighborhood;

    @JsonProperty("localidade")
    private String city;

    @JsonProperty("uf")
    private String state;

    @JsonProperty("cep")
    private String zipCode;

    @JsonProperty("erro")
    private Boolean error;

    /**
     * Formats the address information into a readable string
     * @return formatted address string
     */
    public String formatAddress() {
        StringBuilder address = new StringBuilder();

        if (street != null && !street.isEmpty()) {
            address.append(street);
        }

        if (complement != null && !complement.isEmpty()) {
            if (address.length() > 0) {
                address.append(", ");
            }
            address.append(complement);
        }

        if (neighborhood != null && !neighborhood.isEmpty()) {
            if (address.length() > 0) {
                address.append(", ");
            }
            address.append(neighborhood);
        }

        if (city != null && !city.isEmpty()) {
            if (address.length() > 0) {
                address.append(" - ");
            }
            address.append(city);
        }

        if (state != null && !state.isEmpty()) {
            if (address.length() > 0) {
                address.append(", ");
            }
            address.append(state);
        }

        return address.toString();
    }

}

