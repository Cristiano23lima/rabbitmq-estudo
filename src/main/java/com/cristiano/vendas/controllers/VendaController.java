package com.cristiano.vendas.controllers;

import com.cristiano.vendas.domain.Venda;
import com.cristiano.vendas.domain.enums.StatusVenda;
import com.cristiano.vendas.services.VendaService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class VendaController {
    private final VendaService vendaService;

    @PostMapping("/venda")
    public ResponseEntity<Venda> salveVenda(@RequestBody Venda venda){
        venda.setStatus(StatusVenda.PENDING);

        return ResponseEntity.status(HttpStatus.CREATED).body(vendaService.paySale(venda));
    }
}
