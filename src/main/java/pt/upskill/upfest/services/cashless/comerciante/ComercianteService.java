package pt.upskill.upfest.services.cashless.comerciante;

import pt.upskill.upfest.entities.Comerciante;
import pt.upskill.upfest.models.ComercianteModel;

import java.util.List;

public interface ComercianteService {
    List<Comerciante> listComerciantes(Long idEvento);
    Comerciante createComerciante(Long idEvento, ComercianteModel comercianteModel);
    Comerciante editComerciante(Long idEvento, Long idComerciante, ComercianteModel comercianteModel);
}
