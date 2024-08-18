package br.edu.unipe.api.model.dto;

import lombok.Data;

@Data
public class EnderecoDTO {

    private String cep;
    private String logradouro;
    private String localidade;
    private String bairro;
    private String uf;
}
