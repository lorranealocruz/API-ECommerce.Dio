package br.com.ecommerce.api_ecommerce.dto;

public class StatusPedidoDTO {
    
	private String status;

    public StatusPedidoDTO() {}

    public StatusPedidoDTO(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
