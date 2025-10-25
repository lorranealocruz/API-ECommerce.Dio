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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.ecommerce.api_ecommerce.dto.PedidoCompletoDTO;
import br.com.ecommerce.api_ecommerce.dto.PedidoInsertDTO;
import br.com.ecommerce.api_ecommerce.service.PedidoService;


@RestController
@RequestMapping("/pedidos")
public class PedidoController {
	
	@Autowired
	private PedidoService pedidoService;
	
	@GetMapping
	public ResponseEntity<List<PedidoCompletoDTO>> listarTodos() {
	    List<PedidoCompletoDTO> pedidos = pedidoService.listarTodos();
	    return ResponseEntity.ok(pedidos);
	}
	
	@PostMapping
	public ResponseEntity<PedidoCompletoDTO> inserir (@RequestBody PedidoInsertDTO pddDTO) {
		
		return ResponseEntity.ok(pedidoService.inserir(pddDTO));
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<PedidoCompletoDTO> listarPorNumero (@PathVariable Long id) {
		return ResponseEntity.ok(pedidoService.listarPorNumero(id));
	}
	
	@PutMapping("/{id}/status") 
	public ResponseEntity<PedidoCompletoDTO> alterarStatus(
			@PathVariable Long id,
			@RequestParam String status) {
		return ResponseEntity.ok(pedidoService.alterarStatus(id, status));
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deletar(@PathVariable Long id) {
	    pedidoService.deletar(id);
	    return ResponseEntity.noContent().build();
	}
	

}
