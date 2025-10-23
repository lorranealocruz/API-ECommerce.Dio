package br.com.ecommerce.api_ecommerce.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.ecommerce.api_ecommerce.domain.Categoria;
import br.com.ecommerce.api_ecommerce.domain.Produto;
import br.com.ecommerce.api_ecommerce.repository.CategoriaRepository;
import br.com.ecommerce.api_ecommerce.repository.ProdutoRepository;

@Service
public class ProdutoService {
	@Autowired
	private ProdutoRepository produtoRepository;

	@Autowired
	private CategoriaRepository categoriaRepository;


	public Produto inserir(Produto produto, Long categoriaId) {
		Categoria categoria = categoriaRepository.findById(categoriaId)
				.orElseThrow(() -> new RuntimeException("Categoria não encontrada!"));
		produto.setCategoria(categoria);
		return produtoRepository.save(produto);
	}


	public List<Produto> listar() {
		return produtoRepository.findAll();
	}


	public Produto atualizar(Long id, Produto produto, Long categoriaId) {
		Produto p = produtoRepository.findById(id).orElseThrow(() -> new RuntimeException("Produto não encontrado"));
		Categoria categoria = categoriaRepository.findById(categoriaId)
				.orElseThrow(() -> new RuntimeException("Categoria não encontrada"));

		p.setNome(produto.getNome());
		p.setDescricao(produto.getDescricao());
		p.setPreco(produto.getPreco());
		p.setCategoria(categoria);
		return produtoRepository.save(p);
	}
	
	public void deletar(Long id) {
	    Produto produto = produtoRepository.findById(id)
	        .orElseThrow(() -> new RuntimeException("Produto não encontrado"));

	    produtoRepository.delete(produto);
	}

}
