package br.com.ecommerce.api_ecommerce.domain;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.OneToMany;

public class Produto {
	
	@OneToMany(mappedBy = "id.produto")
	private Set<ItemPedido> itens = new HashSet<>();


}
