package pt.upskill.upfest.services.cashless.carregamentos;

import pt.upskill.upfest.entities.CarregamentoCashless;
import pt.upskill.upfest.entities.MovimentoCashless;
import pt.upskill.upfest.entities.PagamentoCashless;
import pt.upskill.upfest.models.CarregamentoModel;
import pt.upskill.upfest.models.ValidarPagamentoModel;

public interface CarregamentosService {
    double obterSaldo(Long idEvento, String emailParticipante);
    Iterable<MovimentoCashless> obterExtrato(Long idEvento, String emailParticipante);
    CarregamentoCashless carregarConta(Long idEvento, CarregamentoModel carregamentoModel);
    PagamentoCashless validarPagamento(ValidarPagamentoModel validarPagamentoModel);

}
