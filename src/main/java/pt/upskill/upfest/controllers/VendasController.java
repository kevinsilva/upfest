package pt.upskill.upfest.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pt.upskill.upfest.entities.Bilhete;
import pt.upskill.upfest.entities.Evento;
import pt.upskill.upfest.entities.Pagamento;
import pt.upskill.upfest.entities.Participante;
import pt.upskill.upfest.models.ComprarBilheteModel;
import pt.upskill.upfest.models.PagamentoModel;
import pt.upskill.upfest.services.VendasService;

import java.util.List;

@RestController
public class VendasController {

    @Autowired
    VendasService vendasService;

    @PostMapping(value = "/vendas/pagamentos/validar")
    public Pagamento validarPagamento(@RequestBody PagamentoModel pagamentoModel){
        return vendasService.validarPagamento(pagamentoModel);
    }

    @GetMapping("/vendas/pagamentos/listar")
    public List<Pagamento> listarPagamentos(@RequestParam String participante) {
        return vendasService.listarPagamentos(participante);
    }

    @PostMapping(value = "/vendas/bilhetes/comprar")
    public Bilhete comprarBilhete(@RequestBody ComprarBilheteModel comprarBilheteModel){
        return vendasService.comprarBilhete(comprarBilheteModel);
    }

    @GetMapping("/vendas/participantes/listar")
    public List<Participante> listarParticipantes(@RequestParam Long evento) {
        return vendasService.listarParticipantes(evento);
    }
}
