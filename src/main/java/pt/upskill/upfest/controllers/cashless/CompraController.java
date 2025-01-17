package pt.upskill.upfest.controllers.cashless;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pt.upskill.upfest.entities.GastoCashless;
import pt.upskill.upfest.models.CompraModel;
import pt.upskill.upfest.services.cashless.compra.CompraService;

@RestController
@RequestMapping("/cashless")
public class CompraController {

    @Autowired
    CompraService compraService;

    @PostMapping("/{id_evento}/registar_compra")
    public GastoCashless registarCompra(@PathVariable("id_evento") Long idEvento, @RequestBody CompraModel compraModel){
        return compraService.registarCompra(idEvento, compraModel);
    }
}
