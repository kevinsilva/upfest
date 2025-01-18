package pt.upskill.upfest.services.cashless.carregamentos;

import pt.upskill.upfest.entities.CarregamentoCashless;

public interface CarregamentosService {

    CarregamentoCashless carregarConta(Long idEvento, String emailParticipante, double valor);
    double getSaldo(Long idEvento, String email_participante);
}
