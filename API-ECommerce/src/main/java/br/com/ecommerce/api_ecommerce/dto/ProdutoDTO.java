package br.com.ecommerce.api_ecommerce.dto;

public class ProdutoDTO {

	private Long id;
	private String nome;
	private String descricao;
	private Double preco;
	private Integer estoque;
	private Long categoriaId;
	private String categoriaNome;

	public ProdutoDTO(Long id, String nome, String descricao, Double preco, Integer estoque, Long categoriaId, String categoriaNome) {
		super();
		this.id = id;
		this.nome = nome;
		this.descricao = descricao;
		this.preco = preco;
		this.estoque = estoque;
		this.categoriaId = categoriaId;
		this.categoriaNome = categoriaNome;
	}

	public ProdutoDTO() {
		super();

	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Double getPreco() {
		return preco;
	}

	public void setPreco(Double preco) {
		this.preco = preco;
	}
	
	public void setEstoque(Integer estoque) {
		this.estoque = estoque;
	}
	
	public Integer getEstoque() {
		return estoque;
	}

	public Long getCategoriaId() {
		return categoriaId;
	}

	public void setCategoriaId(Long categoriaId) {
		this.categoriaId = categoriaId;
	}

	public String getCategoriaNome() {
		return categoriaNome;
	}

	public void setCategoriaNome(String categoriaNome) {
		this.categoriaNome = categoriaNome;
	}

}
