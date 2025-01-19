package pt.upskill.upfest.services.vendas;

import pt.upskill.upfest.entities.Bilhete;
import pt.upskill.upfest.entities.Entrada;
import pt.upskill.upfest.entities.Pagamento;
import pt.upskill.upfest.entities.Participante;
import pt.upskill.upfest.models.ComprarBilheteModel;
import pt.upskill.upfest.models.PagamentoModel;
import pt.upskill.upfest.models.ValidarEntradaModel;

import java.util.List;

public interface VendasService {
    List<Participante> listParticipantes(Long id);
    List<Pagamento> listPagamentos(String email);
    Bilhete buyBilhete(ComprarBilheteModel bilheteModel);
    Pagamento validatePagamento(PagamentoModel info);
    Entrada validateEntrada(ValidarEntradaModel info);
    int generateReferencia();
    int generateEntidade(Participante participante);
}
