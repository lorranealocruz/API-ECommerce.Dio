package br.com.ecommerce.api_ecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.ecommerce.api_ecommerce.domain.Categoria;
import br.com.ecommerce.api_ecommerce.domain.Produto;

public interface ProdutoRepository extends JpaRepository<Produto, Long>{

	boolean existsByCategoria(Categoria categoria);

}
