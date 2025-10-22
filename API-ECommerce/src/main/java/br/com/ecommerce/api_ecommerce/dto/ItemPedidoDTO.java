package br.com.ecommerce.api_ecommerce.dto;

import br.com.ecommerce.api_ecommerce.domain.ItemPedido;

public class ItemPedidoDTO {

    private String nomeProduto;
    private Integer quantidade;
    private Double valorVenda;
    private Double desconto;
    private Double subtotal;

    public ItemPedidoDTO() {
    }


    public ItemPedidoDTO(ItemPedido item) {
        this.nomeProduto = item.getProduto().getNome();
        this.quantidade = item.getQuantidade();
        this.valorVenda = item.getValorVenda();
        this.desconto = item.getDesconto();
        this.subtotal = (item.getValorVenda() * item.getQuantidade()) - item.getDesconto();
    }


    public String getNomeProduto() {
        return nomeProduto;
    }

    public void setNomeProduto(String nomeProduto) {
        this.nomeProduto = nomeProduto;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }

    public Double getValorVenda() {
        return valorVenda;
    }

    public void setValorVenda(Double valorVenda) {
        this.valorVenda = valorVenda;
    }

    public Double getDesconto() {
        return desconto;
    }

    public void setDesconto(Double desconto) {
        this.desconto = desconto;
    }

    public Double getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(Double subtotal) {
        this.subtotal = subtotal;
    }
}