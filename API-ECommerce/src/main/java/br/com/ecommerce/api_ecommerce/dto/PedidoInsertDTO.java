package br.com.ecommerce.api_ecommerce.dto;

import java.util.List;

public class PedidoInsertDTO {

    private Long clienteId;
    private List<ItemInsertDTO> itens;

    private String codigoCupom;

    

    public PedidoInsertDTO() {
    }

    public PedidoInsertDTO(Long clienteId, List<ItemInsertDTO> itens) {
        this.clienteId = clienteId;
        this.itens = itens;
    }

    public Long getClienteId() {
        return clienteId;
    }

    public void setClienteId(Long clienteId) {
        this.clienteId = clienteId;
    }

    public List<ItemInsertDTO> getItens() {
        return itens;
    }

    public void setItens(List<ItemInsertDTO> itens) {
        this.itens = itens;
    }

    public String getCodigoCupom() {
        return codigoCupom;
    }

    public void setCodigoCupom(String codigoCupom) {
        this.codigoCupom = codigoCupom;
    }
}