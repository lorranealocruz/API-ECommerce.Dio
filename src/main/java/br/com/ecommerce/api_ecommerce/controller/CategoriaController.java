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

import br.com.ecommerce.api_ecommerce.domain.Categoria;
import br.com.ecommerce.api_ecommerce.dto.CategoriaDTO;
import br.com.ecommerce.api_ecommerce.service.CategoriaService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/categorias")
public class CategoriaController {

	@Autowired
	private CategoriaService categoriaService;

	@PostMapping
	public ResponseEntity<Categoria> inserir(@Valid @RequestBody CategoriaDTO categoriaDTO) {
		return ResponseEntity.ok(categoriaService.inserir(categoriaDTO));
	}

	@GetMapping
	public ResponseEntity<List<CategoriaDTO>> listar() {
		return ResponseEntity.ok(categoriaService.listar());
	}

	@GetMapping("/{id}")
	public ResponseEntity<CategoriaDTO> listarPorId(@PathVariable Long id) {
		CategoriaDTO categoria = categoriaService.listarPorId(id);
		return ResponseEntity.ok(categoria);
	}

	@PutMapping("/{id}")
	public ResponseEntity<Categoria> atualizar(@PathVariable Long id, @RequestBody CategoriaDTO dto) {
		return ResponseEntity.ok(categoriaService.atualizar(id, dto));
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deletar(@PathVariable Long id) {
		categoriaService.deletar(id);
		return ResponseEntity.noContent().build();
	}

}
