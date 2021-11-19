package br.com.estudos.crud.dto;

import lombok.*;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ClienteDto {

    private Long id;
    private String nome;
    private String endereco;

}
