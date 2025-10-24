package br.com.ecommerce.api_ecommerce.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.ecommerce.api_ecommerce.domain.Categoria;
import br.com.ecommerce.api_ecommerce.domain.Produto;
import br.com.ecommerce.api_ecommerce.dto.ProdutoDTO;
import br.com.ecommerce.api_ecommerce.repository.CategoriaRepository;
import br.com.ecommerce.api_ecommerce.repository.ProdutoRepository;

@Service
public class ProdutoService {
	@Autowired
	private ProdutoRepository produtoRepository;

	@Autowired
	private CategoriaRepository categoriaRepository;

	// cria um produto
	public Produto inserir(ProdutoDTO produtoDTO) {
		Categoria categoria = categoriaRepository.findById(produtoDTO.getCategoriaId())
				.orElseThrow(() -> new RuntimeException("Categoria não encontrada!"));
		Produto produto = new Produto();
		produto.setNome(produtoDTO.getNome());
		produto.setDescricao(produtoDTO.getDescricao());
		produto.setEstoque(produtoDTO.getEstoque());
		produto.setPreco(produtoDTO.getPreco());
		produto.setCategoria(categoria);
		return produtoRepository.save(produto);
	}

	// lista todos os produtos
	public List<ProdutoDTO> listar() {
		return produtoRepository.findAll().stream().map(p -> {
			ProdutoDTO produtoDTO = new ProdutoDTO();
			produtoDTO.setId(p.getId());
			produtoDTO.setNome(p.getNome());
			produtoDTO.setDescricao(p.getDescricao());
			produtoDTO.setPreco(p.getPreco());
			produtoDTO.setEstoque(p.getEstoque());
			produtoDTO.setCategoriaId(p.getCategoria().getId());
			produtoDTO.setCategoriaNome(p.getCategoria().getNome());
			return produtoDTO;
		}).toList();
	}

	// atualiza um produto
	public Produto atualizar(Long id, ProdutoDTO produtoDTO) {
		Produto produto = produtoRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("Produto não encontrado"));

		Categoria categoria = categoriaRepository.findById(produtoDTO.getCategoriaId())
				.orElseThrow(() -> new RuntimeException("Categoria não encontrada"));

		produto.setNome(produtoDTO.getNome());
		produto.setDescricao(produtoDTO.getDescricao());
		produto.setPreco(produtoDTO.getPreco());
		produto.setEstoque(produtoDTO.getEstoque());
		produto.setCategoria(categoria);

		return produtoRepository.save(produto);
	}

	// lista um produto
	public ProdutoDTO listarPorId(Long id) {
		Produto produto = produtoRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("Produto não encontrado!"));

		ProdutoDTO produtoDTO = new ProdutoDTO();
		produtoDTO.setId(produto.getId());
		produtoDTO.setNome(produto.getNome());
		produtoDTO.setDescricao(produto.getDescricao());
		produtoDTO.setPreco(produto.getPreco());
		produtoDTO.setCategoriaId(produto.getCategoria().getId());
		produtoDTO.setCategoriaNome(produto.getCategoria().getNome());
		return produtoDTO;
	}

	// deleta um produto
	public void deletar(Long id) {
		Produto produto = produtoRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("Produto não encontrado"));
		produtoRepository.delete(produto);
	}

}
