package br.com.ecommerce.api_ecommerce.dto;

public class ItemInsertDTO {

    private Double valorVenda;
    private Double desconto;
    private Integer quantidade;
    private Long idProduto; 

    public ItemInsertDTO() {
    }

    public ItemInsertDTO(Double valorVenda, Double desconto, Integer quantidade, Long idProduto) {
        this.valorVenda = valorVenda;
        this.desconto = desconto;
        this.quantidade = quantidade;
        this.idProduto = idProduto;
    }

    public Long getIdProduto() {
        return idProduto;
    }

    public void setIdProduto(Long idProduto) {
        this.idProduto = idProduto;
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
    
    
}