package pt.upskill.upfest.controllers.cashless;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pt.upskill.upfest.entities.Comerciante;
import pt.upskill.upfest.models.ComercianteModel;
import pt.upskill.upfest.services.cashless.comerciante.ComercianteService;

import java.util.List;

@RestController
@RequestMapping("/cashless")
public class ComercianteController {

    @Autowired
    ComercianteService comercianteService;

    @GetMapping("/{id_evento}/comerciantes/listar")
    public List<Comerciante> listarComerciantes(@PathVariable("id_evento") Long idEvento) {
        return comercianteService.listarComerciantes(idEvento);
    }

    @PostMapping("/{id_evento}/comerciantes/criar")
    public Comerciante criarComerciante(@PathVariable("id_evento") Long idEvento, @RequestBody ComercianteModel comercianteModel) {
        return comercianteService.criarComerciante(idEvento, comercianteModel);
    }

    @PostMapping("/{id_evento}/comerciantes/{id_comerciante}/editar")
    public Comerciante editarComerciante(@PathVariable("id_evento") Long idEvento, @PathVariable("id_comerciante") Long idComerciante, @RequestBody ComercianteModel comercianteModel) {
        return comercianteService.editarComerciante(idEvento, idComerciante, comercianteModel);
    }
}
