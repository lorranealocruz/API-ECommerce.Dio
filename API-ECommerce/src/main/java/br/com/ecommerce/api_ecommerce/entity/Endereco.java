package br.com.ecommerce.api_ecommerce.entity;

import jakarta.persistence.Embeddable;

@Embeddable
public class Endereco {
    private String cep;
    private String logradouro;
    private String bairro;
    private String localidade;
    private String uf;

   
}
