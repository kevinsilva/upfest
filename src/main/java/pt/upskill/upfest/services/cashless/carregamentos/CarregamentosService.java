package pt.upskill.upfest.services.cashless.carregamentos;

import pt.upskill.upfest.entities.CarregamentoCashless;
import pt.upskill.upfest.entities.MovimentoCashless;
import pt.upskill.upfest.entities.PagamentoCashless;
import pt.upskill.upfest.models.CarregamentoModel;
import pt.upskill.upfest.models.ValidarPagamentoModel;

public interface CarregamentosService {
    double getSaldo(Long idEvento, String emailParticipante);
    Iterable<MovimentoCashless> getExtrato(Long idEvento, String emailParticipante);
    CarregamentoCashless rechargeConta(Long idEvento, CarregamentoModel carregamentoModel);
    PagamentoCashless validatePagamento(ValidarPagamentoModel validarPagamentoModel);
}
