package pt.upskill.upfest.services;

import pt.upskill.upfest.entities.Pagamento;
import pt.upskill.upfest.models.PagamentoModel;

import java.util.List;

public interface VendasService {

    Pagamento validarPagamento(PagamentoModel info);

    List<Pagamento> getAllPagamentos();
}
