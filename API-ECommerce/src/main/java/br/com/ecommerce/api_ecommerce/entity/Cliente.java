package br.com.ecommerce.api_ecommerce.entity;

import br.com.ecommerce.api_ecommerce.dto.ClienteInsertDTO;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import org.hibernate.validator.constraints.br.CPF;

@Entity
@Table(name = "tb_cliente")
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    private String telefone;

    @Email
    private String email;

    @CPF
    private String cpf;

    @Valid
    @Embedded
    private Endereco endereco;

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    // Construtor padrão (obrigatório pelo JPA)
    public Cliente() {}

    public Cliente(ClienteInsertDTO dto) {
        this.nome = dto.getNome();
        this.telefone = dto.getTelefone();
        this.email = dto.getEmail();
        this.cpf = dto.getCpf();
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }
}
