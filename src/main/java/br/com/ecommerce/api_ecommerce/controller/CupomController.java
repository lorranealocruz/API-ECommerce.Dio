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
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.ecommerce.api_ecommerce.dto.CupomDTO;
import br.com.ecommerce.api_ecommerce.service.CupomService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/cupons")
public class CupomController {

    @Autowired
    private CupomService service;

    @GetMapping
    public ResponseEntity<List<CupomDTO>> findAll() {
        List<CupomDTO> list = service.findAll();
        return ResponseEntity.ok(list);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CupomDTO> findById(@PathVariable Long id) {
        CupomDTO dto = service.findById(id);
        return ResponseEntity.ok(dto);
    }

    @PostMapping
    public ResponseEntity<CupomDTO> insert(@Valid @RequestBody CupomDTO dto) {
        dto = service.insert(dto);
        var uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(dto.getId()).toUri();
        return ResponseEntity.created(uri).body(dto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CupomDTO> update(@PathVariable Long id, @Valid @RequestBody CupomDTO dto) {
        dto = service.update(id, dto);
        return ResponseEntity.ok(dto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}