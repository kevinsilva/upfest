package pt.upskill.upfest.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import pt.upskill.upfest.entities.Evento;
import pt.upskill.upfest.entities.Pagamento;
import pt.upskill.upfest.models.PagamentoModel;
import pt.upskill.upfest.services.VendasService;

import java.util.List;

@RestController
public class VendasController {

    @Autowired
    VendasService vendasService;

    @PostMapping(value = "/vendas/pagamentos/validar")
    public Pagamento validarPagamento(PagamentoModel pagamentoModel){
        return vendasService.validarPagamento(pagamentoModel);
    }
}
