package br.com.ecommerce.api_ecommerce.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.ecommerce.api_ecommerce.domain.Produto;
import br.com.ecommerce.api_ecommerce.dto.ProdutoDTO;
import br.com.ecommerce.api_ecommerce.service.ProdutoService;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {
	@Autowired
	private ProdutoService produtoService;

	@PostMapping
	public ResponseEntity<Produto> inserir(@RequestBody ProdutoDTO dto) {
		Produto produto = new Produto();
		produto.setNome(dto.getNome());
		produto.setDescricao(dto.getDescricao());
		produto.setPreco(dto.getPreco());
		return ResponseEntity.ok(produtoService.inserir(produto, dto.getCategoriaId()));
	}

	@PutMapping("/{id}")
	public ResponseEntity<Produto> editar(@PathVariable Long id, @RequestBody ProdutoDTO dto) {
		Produto produto = new Produto();
		produto.setNome(dto.getNome());
		produto.setDescricao(dto.getDescricao());
		produto.setPreco(dto.getPreco());
		return ResponseEntity.ok(produtoService.atualizar(id, produto, dto.getCategoriaId()));
	}

	@GetMapping
	public ResponseEntity<List<ProdutoDTO>> listar() {
		List<ProdutoDTO> produtosDTO = produtoService.listar().stream().map(p -> {
			ProdutoDTO dto = new ProdutoDTO();
			dto.setId(p.getId());
			dto.setNome(p.getNome());
			dto.setDescricao(p.getDescricao());
			dto.setPreco(p.getPreco());
			dto.setCategoriaId(p.getCategoria().getId());
			dto.setCategoriaNome(p.getCategoria().getNome());
			return dto;
		}).toList();
		return ResponseEntity.ok(produtosDTO);
	}
	
	@DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        produtoService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
