package pt.upskill.upfest.controllers.vendas;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pt.upskill.upfest.entities.*;
import pt.upskill.upfest.models.ComprarBilheteModel;
import pt.upskill.upfest.models.PagamentoModel;
import pt.upskill.upfest.models.ValidarEntradaModel;
import pt.upskill.upfest.services.vendas.VendasService;

import java.util.List;

@RestController
public class VendasController {

    @Autowired
    VendasService vendasService;

    @PostMapping(value = "/vendas/pagamentos/validar")
    public Pagamento validatePagamento(@RequestBody PagamentoModel pagamentoModel){
        return vendasService.validatePagamento(pagamentoModel);
    }

    @GetMapping("/vendas/pagamentos/listar")
    public List<Pagamento> listPagamentos(@RequestParam String participante) {
        return vendasService.listPagamentos(participante);
    }

    @PostMapping(value = "/vendas/bilhetes/comprar")
    public Bilhete buyBilhete(@RequestBody ComprarBilheteModel comprarBilheteModel){
        return vendasService.buyBilhete(comprarBilheteModel);
    }

    @GetMapping("/vendas/participantes/listar")
    public List<Participante> listParticipantes(@RequestParam Long evento) {
        return vendasService.listParticipantes(evento);
    }

    @PostMapping("/vendas/bilhetes/validar_entrada")
    public Entrada validateEntrada(@RequestBody ValidarEntradaModel validarEntradaModel){
        return vendasService.validateEntrada(validarEntradaModel);
    }
}
