package br.com.ecommerce.api_ecommerce.dto;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class VendasDiaDTO {
    private LocalDate data;
    private Long totalPedidos;
    private Double valorTotalVendas;
    private Double ticketMedio;
    private Long pedidosPagos;
    private Long pedidosPendentes;

}
