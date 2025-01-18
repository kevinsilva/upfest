package pt.upskill.upfest.services.cashless.carregamentos;

import pt.upskill.upfest.entities.CarregamentoCashless;
import pt.upskill.upfest.entities.PagamentoCashless;
import pt.upskill.upfest.models.ValidarPagamentoModel;

public interface CarregamentosService {
    double getSaldo(Long idEvento, String emailParticipante);
    CarregamentoCashless carregarConta(Long idEvento, String emailParticipante, double valor);
    PagamentoCashless validarPagamento(ValidarPagamentoModel validarPagamentoModel);

}
