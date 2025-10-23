package br.com.ecommerce.api_ecommerce.domain;

import java.io.Serializable;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "item_pedido")
public class ItemPedido implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private ItemPedidoPK id = new ItemPedidoPK();

	private Double valorVenda;
	private Double desconto;
	private Integer quantidade;

	public ItemPedido() {
	}

	public ItemPedido(Pedido pedido, Produto produto, Double valorVenda, Double desconto, Integer quantidade) {
		id.setPedido(pedido);
		id.setProduto(produto);
		this.valorVenda = valorVenda;
		this.desconto = desconto;
		this.quantidade = quantidade;
	}

	// Getters e Setters

	public ItemPedidoPK getId() {
		return id;
	}

	public void setId(ItemPedidoPK id) {
		this.id = id;
	}

	public Pedido getPedido() {
		return id.getPedido();
	}

	public Produto getProduto() {
		return id.getProduto();
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

	// Total de cada item (opcional, mas útil)
	public Double getSubTotal() {
		return (valorVenda - desconto) * quantidade;
	}

}
