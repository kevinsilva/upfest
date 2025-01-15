package pt.upskill.upfest.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pt.upskill.upfest.entities.ContaCashless;
import pt.upskill.upfest.entities.MovimentoCashless;
import pt.upskill.upfest.entities.Pagamento;
import pt.upskill.upfest.entities.PagamentoCashless;
import pt.upskill.upfest.services.CashlessService;

import java.util.List;

@RestController
public class CashlessController {

    @Autowired
    private CashlessService cashlessService;

    @GetMapping("/{id_evento}/saldo")
    public ResponseEntity<Double> consultarSaldo(
            @PathVariable("id_evento") Long eventoId,
            @RequestParam("participante") String participanteEmail) {
        return ResponseEntity.ok(cashlessService.consultarSaldo(eventoId, participanteEmail));
    }

    @GetMapping("/{id_evento}/extrato")
    public ResponseEntity<List<MovimentoCashless>> consultarExtrato(
            @PathVariable("id_evento") Long eventoId,
            @RequestParam("participante") String participanteEmail) {
        return ResponseEntity.ok(cashlessService.consultarExtrato(eventoId, participanteEmail));
    }

    @GetMapping("/{id_evento}/extrato")
    public ResponseEntity<byte[]> gerarExtratoExcel(
            @PathVariable("id_evento") Long eventoId,
            @RequestParam("participante") String participanteEmail,
            @RequestParam("excel") boolean gerarExcel) {
        if (gerarExcel) {
            byte[] arquivo = cashlessService.gerarExtratoExcel(eventoId, participanteEmail);
            return ResponseEntity.ok()
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=extrato.xlsx")
                    .body(arquivo);
        }
        return ResponseEntity.badRequest().build();
    }

    @PostMapping("/{id_evento}/carregar")
    public ResponseEntity<ContaCashless> carregarSaldo(
            @PathVariable("id_evento") Long eventoId,
            @RequestParam("participante") String participanteEmail,
            @RequestParam("valor") double valor) {
        return ResponseEntity.ok(cashlessService.carregarSaldo(eventoId, participanteEmail, valor));
    }

    @PostMapping("/validar_pagamento")
    public ResponseEntity<PagamentoCashless> validarPagamento(@RequestBody Pagamento pagamento) {
        return ResponseEntity.ok(cashlessService.validarPagamento(pagamento));
    }
}

