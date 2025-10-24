package br.com.ecommerce.api_ecommerce.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.ecommerce.api_ecommerce.domain.Categoria;
import br.com.ecommerce.api_ecommerce.dto.CategoriaDTO;
import br.com.ecommerce.api_ecommerce.repository.CategoriaRepository;
import br.com.ecommerce.api_ecommerce.repository.ProdutoRepository;

@Service
public class CategoriaService {

	@Autowired
	private CategoriaRepository categoriaRepository;

	@Autowired
	private ProdutoRepository produtoRepository;

	// criar categoria
	public Categoria inserir(CategoriaDTO categoriaDTO) {
		Categoria categoria = new Categoria();
		categoria.setNome(categoriaDTO.getNome());
		categoria.setDescricao(categoriaDTO.getDescricao());
		return categoriaRepository.save(categoria);
	}

	// atualizar
	public Categoria atualizar(Long id, CategoriaDTO categoriaDTO) {
		Categoria categoria = categoriaRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("Categoria não encontrada!"));

		categoria.setNome(categoriaDTO.getNome());
		categoria.setDescricao(categoriaDTO.getDescricao());

		return categoriaRepository.save(categoria);
	}

	// listar todos
	public List<CategoriaDTO> listar() {
		return categoriaRepository.findAll().stream().map(c -> {
			CategoriaDTO categoriaDTO = new CategoriaDTO();
			categoriaDTO.setId(c.getId());
			categoriaDTO.setNome(c.getNome());
			categoriaDTO.setDescricao(c.getDescricao());
			return categoriaDTO;
		}).toList();
	}

	// lista por id
	public CategoriaDTO listarPorId(Long id) {
		Categoria categoria = categoriaRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("Categoria não encontrada!"));

		CategoriaDTO categoriaDTO = new CategoriaDTO();
		categoriaDTO.setId(categoria.getId());
		categoriaDTO.setNome(categoria.getNome());
		categoriaDTO.setDescricao(categoria.getDescricao());
		return categoriaDTO;
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
