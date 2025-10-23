package br.com.ecommerce.api_ecommerce.dto;

import br.com.ecommerce.api_ecommerce.domain.Pedido;
import br.com.ecommerce.api_ecommerce.domain.Produto;

public class ItemInsertDTO {

	private Double valorVenda;
	private Double desconto;
	private Integer quantidade;
	private Produto produto;
	private Pedido pedido;

	public ItemInsertDTO() {
	}

	public ItemInsertDTO(Double valorVenda, Double desconto, Integer quantidade, Produto produto, Pedido pedido) {
		super();
		this.valorVenda = valorVenda;
		this.desconto = desconto;
		this.quantidade = quantidade;
		this.produto = produto;
		this.pedido = pedido;
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

	public Integer getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}

	public Pedido getPedido() {
		return pedido;
	}

	public void setPedido(Pedido pedido) {
		this.pedido = pedido;
	}
}