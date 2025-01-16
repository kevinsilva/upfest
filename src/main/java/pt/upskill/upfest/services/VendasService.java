package pt.upskill.upfest.services;

import pt.upskill.upfest.entities.Bilhete;
import pt.upskill.upfest.entities.Pagamento;
import pt.upskill.upfest.entities.Participante;
import pt.upskill.upfest.models.ComprarBilheteModel;
import pt.upskill.upfest.models.PagamentoModel;

import java.util.List;

public interface VendasService {

    List<Participante> listarParticipantes(Long id);

    List<Pagamento> listarPagamentos(String email);

    //2.1.bilhete
    Bilhete comprarBilhete(ComprarBilheteModel bilheteModel);

    Pagamento validarPagamento(PagamentoModel info);

    //2.1.bilhete
    Participante getParticipante(Long id);
}
