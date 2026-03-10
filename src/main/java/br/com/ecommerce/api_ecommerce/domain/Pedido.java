package br.com.ecommerce.api_ecommerce.domain;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import br.com.ecommerce.api_ecommerce.entity.Cliente;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "pedido")
public class Pedido implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column
	private LocalDate dataPedido;

	@Enumerated(EnumType.STRING)
	private StatusPedido status;

	@Column
	private Double valorVenda;

	@Column
	private Double desconto;

	@Column
	private Integer quantidade;
	
	@Column 
	private Double ValorTotal;

	@ManyToOne
	@JoinColumn(name = "id_cliente", nullable = false)
	private Cliente cliente;

	@OneToMany(mappedBy = "id.pedido", cascade = CascadeType.ALL, orphanRemoval = true)
	private Set<ItemPedido> itens = new HashSet<>();

    @ManyToOne
    @JoinColumn(name = "id_cupom_aplicado", nullable = true)
    private Cupom cupomAplicado;

    private Double valorDescontoTotal = 0.0;


	public Cupom getCupomAplicado() {
        return cupomAplicado;
    }

    public void setCupomAplicado(Cupom cupomAplicado) {
        this.cupomAplicado = cupomAplicado;
    }

    public Double getValorDescontoTotal() {
        return valorDescontoTotal;
    }

    public void setValorDescontoTotal(Double valorDescontoTotal) {
        this.valorDescontoTotal = valorDescontoTotal;
    }

    public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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
	
	public Double getValorTotal() {
		return ValorTotal;
	}
	
	public void setValorTotal(Double ValorTotal) {
		this.ValorTotal = ValorTotal;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Set<ItemPedido> getItens() {
		return itens;
	}

	public void setItens(Set<ItemPedido> itens) {
		this.itens = itens;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
