package pt.upskill.upfest.services.cashless.comerciante;

import pt.upskill.upfest.entities.Comerciante;
import pt.upskill.upfest.entities.ProdutoComerciante;
import pt.upskill.upfest.models.NovoComerciante;
import pt.upskill.upfest.models.NovoProdutoComerciante;

import java.util.List;

public interface ComercianteService {
    // RegistarCompra
    // TODO

    // Comerciante
    List<Comerciante> listarComerciantes(Long idEvento);
    Comerciante criarComerciante(Long idEvento, NovoComerciante novoComerciante);
    Comerciante editarComerciante(Long idEvento, Long idComerciante, NovoComerciante novoComerciante);
}
