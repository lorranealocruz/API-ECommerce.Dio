package br.com.ecommerce.api_ecommerce.dto;

import java.time.LocalDateTime;
import java.util.List;

public class PedidoCompletoDTO {

	private Long id;
    private String clienteNome;
    private LocalDateTime dataPedido;
    private StatusPedido status;
    private List<ItemPedidoDTO> itens;
    private Double total;
    
    public PedidoCompletoDTO() {}


	public PedidoCompletoDTO(Long id, String clienteNome, LocalDateTime dataPedido, StatusPedido status,
			List<ItemPedidoDTO> itens, Double total) {
		super();
		this.id = id;
		this.clienteNome = clienteNome;
		this.dataPedido = dataPedido;
		this.status = status;
		this.itens = itens;
		this.total = total;
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


	public LocalDateTime getDataPedido() {
		return dataPedido;
	}


	public void setDataPedido(LocalDateTime dataPedido) {
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
		return total;
	}


	public void setTotal(Double total) {
		this.total = total;
	}
}
