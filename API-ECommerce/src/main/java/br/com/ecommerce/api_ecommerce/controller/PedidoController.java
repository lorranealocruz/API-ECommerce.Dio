package br.com.ecommerce.api_ecommerce.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.ecommerce.api_ecommerce.dto.PedidoCompletoDTO;
import br.com.ecommerce.api_ecommerce.service.PedidoService;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/pedidos")
public class PedidoController {
	
	@Autowired
	private PedidoService pedidoService;
	
	@PostMapping
	public ResponseEntity<PedidoCompletoDTO> inserir (@RequestBody PedidoCompletoDTO pddDTO) {
		
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
	

}
