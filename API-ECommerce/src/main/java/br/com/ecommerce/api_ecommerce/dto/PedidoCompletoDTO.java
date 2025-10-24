package br.com.ecommerce.api_ecommerce.dto;

import java.time.LocalDate;
import java.util.List;

import br.com.ecommerce.api_ecommerce.domain.StatusPedido;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

public class PedidoCompletoDTO {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
    private String clienteNome;
    private LocalDate dataPedido;
    private StatusPedido status;
    private List<ItemPedidoDTO> itens;
    private Double Valortotal;
    
    public PedidoCompletoDTO() {}


	public PedidoCompletoDTO(Long id, String clienteNome, LocalDate dataPedido, StatusPedido status,
			List<ItemPedidoDTO> itens, Double Valortotal) {
		super();
		this.id = id;
		this.clienteNome = clienteNome;
		this.dataPedido = dataPedido;
		this.status = status;
		this.itens = itens;
		this.Valortotal = Valortotal;
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


	public Double getTotal() {
		return Valortotal;
	}


	public void setTotal(Double Valortotal) {
		this.Valortotal = Valortotal;
	}
}
