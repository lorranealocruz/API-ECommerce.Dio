package br.com.ecommerce.api_ecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.ecommerce.api_ecommerce.domain.Categoria;

public interface CategoriaRepository extends JpaRepository<Categoria, Long>{
	
}
