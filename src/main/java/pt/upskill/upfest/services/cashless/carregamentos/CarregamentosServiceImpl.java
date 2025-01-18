package pt.upskill.upfest.services.cashless.carregamentos;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pt.upskill.upfest.entities.*;
import pt.upskill.upfest.enums.TipoMovimento;
import pt.upskill.upfest.repositories.*;
import pt.upskill.upfest.services.VendasService;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class CarregamentosServiceImpl implements CarregamentosService {

    @Autowired
    EventoRepository eventoRepository;
    @Autowired
    ParticipanteRepository participanteRepository;
    @Autowired
    ContaCashlessRepository contaCashlessRepository;
    @Autowired
    MovimentoCashlessRepository movimentoCashlessRepository;
    @Autowired
    PagamentoRepository pagamentoRepository;
    @Autowired
    VendasService vendasService;

    @Override
    public CarregamentoCashless carregarConta(Long idEvento, String emailParticipante, double valor) {
        if(valor <= 0) throw new IllegalArgumentException("Valor must be greater than 0.");

        Evento evento = eventoRepository.findById(idEvento).orElseThrow(() -> new IllegalArgumentException("Evento with id " + idEvento + "not found"));
        Participante participante = participanteRepository.findByEmail(emailParticipante).orElseThrow(() -> new IllegalArgumentException("Participante not found."));

        ContaCashless contaCashless = contaCashlessRepository.findByParticipanteAndEvento(participante, evento);
        if(contaCashless == null || contaCashless.getId() == null) {
            throw new IllegalArgumentException("There is no account for given event.");
        }

        PagamentoCashless pagamentoCashless = new PagamentoCashless();
        pagamentoCashless.setContaCashless(contaCashless);
        pagamentoCashless.setEntidade(vendasService.gerarEntidade(participante));
        pagamentoCashless.setReferencia(vendasService.gerarReferencia());
        pagamentoCashless.setValor(valor);
        pagamentoCashless.setData_compra(LocalDateTime.now());
        pagamentoCashless.setData_validado(null);

        pagamentoCashless = (PagamentoCashless) pagamentoRepository.save(pagamentoCashless);

        CarregamentoCashless carregamentoCashless = new CarregamentoCashless();
        carregamentoCashless.setTipoMovimento(TipoMovimento.RECARGA);
        carregamentoCashless.setContaCashless(contaCashless);
        carregamentoCashless.setValor(valor);
        carregamentoCashless.setSaldo(contaCashless.getValorAtual() + valor);
        carregamentoCashless.setData(LocalDateTime.now());
        carregamentoCashless.setPagamentoCashless(pagamentoCashless);

        return movimentoCashlessRepository.save(carregamentoCashless);
    }

    @Override
    public double getSaldo(Long idEvento, String emailParticipante) {
        Evento evento = eventoRepository.findById(idEvento).orElseThrow(() -> new IllegalArgumentException("Evento with id " + idEvento + "not found"));
        Participante participante = participanteRepository.findByEmail(emailParticipante).orElseThrow(() -> new IllegalArgumentException("Participante not found."));
        ContaCashless contaCashless = contaCashlessRepository.findByParticipanteAndEvento(participante, evento);
        if(contaCashless == null || contaCashless.getId() == null) {
            return 0;
        }
        return contaCashless.getValorAtual();
    }

}