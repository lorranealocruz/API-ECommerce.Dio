package br.com.ecommerce.api_ecommerce.controller;

import br.com.ecommerce.api_ecommerce.dto.TransportadoraDTO;
import br.com.ecommerce.api_ecommerce.service.TransportadoraService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/transportadoras")
public class TransportadoraController {

    @Autowired
    private TransportadoraService servico;


    @GetMapping
    public ResponseEntity<List<TransportadoraDTO>> listarTodas() {
        List<TransportadoraDTO> lista = servico.listarTodas();
        return ResponseEntity.ok().body(lista);
    }

 
    @GetMapping(value = "/{id}")
    public ResponseEntity<TransportadoraDTO> buscarPorId(@PathVariable Long id) {
        TransportadoraDTO dto = servico.buscarPorId(id);
        return ResponseEntity.ok().body(dto);
    }

 
    @PostMapping
    public ResponseEntity<TransportadoraDTO> inserir(@Valid @RequestBody TransportadoraDTO dto) {
        dto = servico.inserir(dto);
     
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(dto.getId()).toUri();
        return ResponseEntity.created(uri).body(dto);
    }

   

    @PutMapping(value = "/{id}")
    public ResponseEntity<TransportadoraDTO> atualizar(@PathVariable Long id, @Valid @RequestBody TransportadoraDTO dto) {
        dto = servico.atualizar(id, dto);
        return ResponseEntity.ok().body(dto);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        servico.deletar(id);

        return ResponseEntity.noContent().build();
    }
}