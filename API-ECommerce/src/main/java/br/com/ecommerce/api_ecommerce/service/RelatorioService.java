package br.com.ecommerce.api_ecommerce.service;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.ecommerce.api_ecommerce.domain.StatusPedido;
import br.com.ecommerce.api_ecommerce.dto.VendasDiaDTO;
import br.com.ecommerce.api_ecommerce.repository.PedidoRepository;

@Service
public class RelatorioService {

    @Autowired
    private PedidoRepository pedidoRepository;

    public VendasDiaDTO obterVendasDoDia(LocalDate data) {
        // Se não passar data, usa hoje
        if (data == null) {
            data = LocalDate.now();
        }

        Long totalPedidos = pedidoRepository.countPedidosPorData(data);
        Double valorTotalVendas = pedidoRepository.sumValorTotalPorData(data);
        Long pedidosPagos = pedidoRepository.countPedidosPorDataEStatus(data, StatusPedido.PAGO);
        Long pedidosPendentes = pedidoRepository.countPedidosPendentesPorData(data);

        Double ticketMedio = 0.0;
        if (totalPedidos > 0 && valorTotalVendas > 0) {
            ticketMedio = valorTotalVendas / totalPedidos;
        }


        if (valorTotalVendas == null) {
            valorTotalVendas = 0.0;
        }

        return new VendasDiaDTO(
                data,
                totalPedidos,
                valorTotalVendas,
                ticketMedio,
                pedidosPagos,
                pedidosPendentes);
    }

    public VendasDiaDTO obterVendasHoje() {
        return obterVendasDoDia(LocalDate.now());
    }
}
