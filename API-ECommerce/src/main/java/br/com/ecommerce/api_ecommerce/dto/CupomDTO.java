package br.com.ecommerce.api_ecommerce.dto;

import java.time.LocalDate;
import br.com.ecommerce.api_ecommerce.domain.Cupom;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CupomDTO {

    private Long id;

    @NotBlank(message = "Código do cupom é obrigatório")
    private String codigo;

    @NotNull(message = "Percentual de desconto é obrigatório")
    @Positive(message = "Percentual de desconto deve ser positivo")
    private Double percentualDesconto;

    @FutureOrPresent(message = "Data de validade deve ser hoje ou no futuro")
    private LocalDate dataValidade;

    private Boolean ativo;

    public CupomDTO(Cupom entity) {
        this.id = entity.getId();
        this.codigo = entity.getCodigo();
        this.percentualDesconto = entity.getPercentualDesconto();
        this.dataValidade = entity.getDataValidade();
        this.ativo = entity.getAtivo();
    }
}