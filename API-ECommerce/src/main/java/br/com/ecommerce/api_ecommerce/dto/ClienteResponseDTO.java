package br.com.ecommerce.api_ecommerce.dto;

import br.com.ecommerce.api_ecommerce.entity.Cliente;

public class ClienteResponseDTO {

    private Long id;
    private String nome;
    private String telefone;
    private String email;
    private String cpf;

    public ClienteResponseDTO(Cliente entity) {
        this.id = entity.getId();
        this.nome = entity.getNome();
        this.telefone = entity.getTelefone();
        this.email = entity.getEmail();
        this.cpf = entity.getCpf();
    }

    // Getters
    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getTelefone() {
        return telefone;
    }

    public String getEmail() {
        return email;
    }

    public String getCpf() {
        return cpf;
    }
}
