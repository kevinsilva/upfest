package pt.upskill.upfest.services.cashless.compra;

import pt.upskill.upfest.entities.GastoCashless;
import pt.upskill.upfest.models.CompraModel;

public interface CompraService {
    GastoCashless registarCompra(Long idEvento, CompraModel compraModel);
}
