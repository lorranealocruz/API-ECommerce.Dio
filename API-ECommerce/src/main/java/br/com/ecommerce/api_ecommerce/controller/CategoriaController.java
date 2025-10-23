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

@RestController
@RequestMapping("/categorias")
public class CategoriaController {

	@Autowired
	private CategoriaService categoriaService;

	@PostMapping
	public ResponseEntity<CategoriaDTO> inserir(@RequestBody CategoriaDTO dto) {
	    Categoria categoria = new Categoria();
	    categoria.setNome(dto.getNome());
	    categoria.setDescricao(dto.getDescricao());

	    Categoria categoriaSalva = categoriaService.inserir(categoria);

	    // Retornar DTO
		CategoriaDTO resposta = new CategoriaDTO();
		resposta.setId(categoriaSalva.getId());
		resposta.setNome(categoriaSalva.getNome());
		resposta.setDescricao(categoriaSalva.getDescricao());

		return ResponseEntity.ok(resposta);
	}

	@GetMapping
	public ResponseEntity<List<Categoria>> listar() {
		return ResponseEntity.ok(categoriaService.listar());
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Categoria> atualizar (@PathVariable Long id, @RequestBody CategoriaDTO dto){
		Categoria categoria = new Categoria();
		categoria.setNome(dto.getNome());
        categoria.setDescricao(dto.getDescricao());
        return ResponseEntity.ok(categoriaService.atualizar(id, categoria));
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deletar(@PathVariable Long id) {
	    categoriaService.deletar(id);
	    return ResponseEntity.noContent().build(); // retorna 204 No Content
	}
	
}
