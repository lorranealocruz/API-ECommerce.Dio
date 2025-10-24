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
import jakarta.validation.Valid;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {
	@Autowired
	private ProdutoService produtoService;

	@PostMapping
	public ResponseEntity<Produto> inserir(@Valid @RequestBody ProdutoDTO dto) {
		return ResponseEntity.ok(produtoService.inserir(dto));
	}

	@PutMapping("/{id}")
	public ResponseEntity<Produto> editar(@PathVariable Long id, @Valid @RequestBody ProdutoDTO dto) {
		return ResponseEntity.ok(produtoService.atualizar(id, dto));
	}

	@GetMapping
	public ResponseEntity<List<ProdutoDTO>> listarTodos() {
		return ResponseEntity.ok(produtoService.listar());
	}

	@GetMapping("/{id}")
	public ResponseEntity<ProdutoDTO> listarPorId(@PathVariable Long id) {
		ProdutoDTO produtoDTO = produtoService.listarPorId(id);
		return ResponseEntity.ok(produtoDTO);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deletar(@PathVariable Long id) {
		produtoService.deletar(id);
		return ResponseEntity.noContent().build();
	}
}
