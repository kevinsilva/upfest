package pt.upskill.upfest.services;

import pt.upskill.upfest.entities.ContaCashless;
import pt.upskill.upfest.entities.MovimentoCashless;
import pt.upskill.upfest.entities.Pagamento;
import pt.upskill.upfest.entities.PagamentoCashless;

import java.util.List;

public interface CashlessService {
    double consultarSaldo(Long eventoId, String participanteEmail);
    List<MovimentoCashless> consultarExtrato(Long eventoId, String participanteEmail);
    byte[] gerarExtratoExcel(Long eventoId, String participanteEmail);
    ContaCashless carregarSaldo(Long eventoId, String participanteEmail, double valor);
    PagamentoCashless validarPagamento(Pagamento pagamento);
}

