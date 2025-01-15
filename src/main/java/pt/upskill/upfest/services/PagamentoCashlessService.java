package pt.upskill.upfest.services;

import pt.upskill.upfest.entities.Pagamento;
import pt.upskill.upfest.entities.PagamentoCashless;

public interface PagamentoCashlessService {
    PagamentoCashless registrarPagamento(Long contaId, Pagamento pagamento);
}

