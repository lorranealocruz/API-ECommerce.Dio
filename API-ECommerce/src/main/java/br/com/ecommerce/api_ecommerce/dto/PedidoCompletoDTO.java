package br.com.ecommerce.api_ecommerce.dto;

import java.time.LocalDate;
import java.util.List;

import br.com.ecommerce.api_ecommerce.domain.StatusPedido;

public class PedidoCompletoDTO {

    private Long id;
    private String clienteNome;
    private LocalDate dataPedido;
    private StatusPedido status;
    private List<ItemPedidoDTO> itens;
    private Double valorTotal;
    private String codigoCupomAplicado;
    private Double valorDescontoAplicado;

    public PedidoCompletoDTO() {}

    public PedidoCompletoDTO(Long id, String clienteNome, LocalDate dataPedido, StatusPedido status,
            List<ItemPedidoDTO> itens, Double valorTotal, String codigoCupomAplicado, Double valorDescontoAplicado) {
        super();
        this.id = id;
        this.clienteNome = clienteNome;
        this.dataPedido = dataPedido;
        this.status = status;
        this.itens = itens;
        this.valorTotal = valorTotal;
        this.codigoCupomAplicado = codigoCupomAplicado;
        this.valorDescontoAplicado = valorDescontoAplicado;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getClienteNome() {
        return clienteNome;
    }

    public void setClienteNome(String clienteNome) {
        this.clienteNome = clienteNome;
    }

    public LocalDate getDataPedido() {
        return dataPedido;
    }

    public void setDataPedido(LocalDate dataPedido) {
        this.dataPedido = dataPedido;
    }

    public StatusPedido getStatus() {
        return status;
    }

    public void setStatus(StatusPedido status) {
        this.status = status;
    }

    public List<ItemPedidoDTO> getItens() {
        return itens;
    }

    public void setItens(List<ItemPedidoDTO> itens) {
        this.itens = itens;
    }

    public Double getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(Double valorTotal) {
        this.valorTotal = valorTotal;
    }

    public String getCodigoCupomAplicado() {
        return codigoCupomAplicado;
    }

    public void setCodigoCupomAplicado(String codigoCupomAplicado) {
        this.codigoCupomAplicado = codigoCupomAplicado;
    }

    public Double getValorDescontoAplicado() {
        return valorDescontoAplicado;
    }

    public void setValorDescontoAplicado(Double valorDescontoAplicado) {
        this.valorDescontoAplicado = valorDescontoAplicado;
    }
}