package pt.upskill.upfest.services.cashless.comerciante;

import pt.upskill.upfest.entities.Comerciante;
import pt.upskill.upfest.models.ComercianteModel;

import java.util.List;

public interface ComercianteService {
    List<Comerciante> listarComerciantes(Long idEvento);
    Comerciante criarComerciante(Long idEvento, ComercianteModel comercianteModel);
    Comerciante editarComerciante(Long idEvento, Long idComerciante, ComercianteModel comercianteModel);
}
