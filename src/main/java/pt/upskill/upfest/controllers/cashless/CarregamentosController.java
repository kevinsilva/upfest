package pt.upskill.upfest.controllers.cashless;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pt.upskill.upfest.entities.CarregamentoCashless;
import pt.upskill.upfest.services.cashless.carregamentos.CarregamentosService;

@RestController
@RequestMapping("/cashless")
public class CarregamentosController {

    @Autowired
    CarregamentosService carregamentosService;

    @GetMapping("/{id_evento}/saldo")
    public double getSaldo(@PathVariable("id_evento") Long idEvento, @RequestParam("participante") String participante) {
        return carregamentosService.getSaldo(idEvento, participante);
    }

    @PostMapping("/{id_evento}/carregar")
    public CarregamentoCashless carregarConta(@PathVariable("id_evento") Long idEvento,
                                               @RequestParam("participante") String participante, @RequestParam("valor") double valor) {
        return carregamentosService.carregarConta(idEvento, participante, valor);
    }

}
