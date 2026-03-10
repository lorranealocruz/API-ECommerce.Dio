package br.com.ecommerce.api_ecommerce.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import br.com.ecommerce.api_ecommerce.service.RelatorioService;
import br.com.ecommerce.api_ecommerce.dto.VendasDiaDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestParam;
import java.time.LocalDate;

@RestController
@RequestMapping("/relatorios")
public class RelatorioController {
    
    @Autowired
    private RelatorioService relatorioService;

    @GetMapping("/vendas-hoje")
    public ResponseEntity<VendasDiaDTO> obterVendasHoje() {
        VendasDiaDTO vendas = relatorioService.obterVendasHoje();
        return ResponseEntity.ok(vendas);
    }

    @GetMapping("/vendas-dia")
    public ResponseEntity<VendasDiaDTO> obterVendasDia(@RequestParam LocalDate data) {
        VendasDiaDTO vendas = relatorioService.obterVendasDoDia(data);
        return ResponseEntity.ok(vendas);
    }

}
