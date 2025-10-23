package br.com.ecommerce.api_ecommerce.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.ecommerce.api_ecommerce.domain.Categoria;
import br.com.ecommerce.api_ecommerce.repository.CategoriaRepository;
import br.com.ecommerce.api_ecommerce.repository.ProdutoRepository;

@Service
public class CategoriaService {

	@Autowired
	private CategoriaRepository categoriaRepository;

	@Autowired
	private ProdutoRepository produtoRepository;

	// criar categoria
	public Categoria inserir(Categoria categoria) {
		return categoriaRepository.save(categoria);
	}

	// atualizar
	public Categoria atualizar(Long id, Categoria categoria) {
		Categoria c = categoriaRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("Categoria não encontrada!"));
		c.setNome(categoria.getNome());
		c.setDescricao(categoria.getDescricao());
		c.setProdutos(categoria.getProdutos());
		return categoriaRepository.save(c);
	}

	// listar
	public List<Categoria> listar() {
		return categoriaRepository.findAll();
	}

	// deletar
	public void deletar(Long id) {
		Categoria categoria = categoriaRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("Categoria não encontrada"));

		boolean possuiProdutos = produtoRepository.existsByCategoria(categoria);
		if (possuiProdutos) {
			throw new RuntimeException("Não é possível deletar uma categoria que possui produtos vinculados.");
		}

		categoriaRepository.delete(categoria);
	}

}
