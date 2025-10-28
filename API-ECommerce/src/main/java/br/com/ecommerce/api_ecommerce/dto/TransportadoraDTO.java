package br.com.ecommerce.api_ecommerce.dto;


import br.com.ecommerce.api_ecommerce.domain.Transportadora;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
public class TransportadoraDTO {

    private Long id;

    @NotBlank(message = "O campo 'nome' é obrigatório")
    private String nome;

    private String telefone;

    @NotNull(message = "A 'taxaBaseFrete' é obrigatória")
    @Positive(message = "A taxa base deve ser um valor positivo")
    private Double taxaBaseFrete;


    public TransportadoraDTO(Transportadora entidade) {
        this.id = entidade.getId();
        this.nome = entidade.getNome();
        this.telefone = entidade.getTelefone();
        this.taxaBaseFrete = entidade.getTaxaBaseFrete();
    }
}