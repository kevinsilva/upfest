package pt.upskill.upfest.services.cashless.carregamentos;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pt.upskill.upfest.entities.*;
import pt.upskill.upfest.repositories.*;

import java.time.LocalDateTime;

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

    @Override
    public CarregamentoCashless carregarConta(Long idEvento, String emailParticipante, double valor) {
        if(valor <= 0) throw new IllegalArgumentException("Valor must be greater than 0.");

        Evento evento = eventoRepository.findById(idEvento).orElseThrow(() -> new IllegalArgumentException("Evento with id " + idEvento + "not found"));
        Participante participante = participanteRepository.findByEmail(emailParticipante);

        ContaCashless contaCashless = contaCashlessRepository.findByParticipanteAndEvento(participante, evento);
        if(contaCashless == null || contaCashless.getId() == null) {
            throw new IllegalArgumentException("There is no account for given event.");
        }

        PagamentoCashless pagamentoCashless = new PagamentoCashless();
        pagamentoCashless.setContaCashless(contaCashless);
        pagamentoCashless.setValor(valor);
        pagamentoCashless.setEntidade();
        pagamentoCashless.setData_compra(LocalDateTime.now());


        CarregamentoCashless carregamentoCashless = new CarregamentoCashless();
        //coisas



        pagamentoCashless = (PagamentoCashless) pagamentoRepository.save(pagamentoCashless);

        carregamentoCashless.setPagamentoCashless(pagamentoCashless);

        return movimentoCashlessRepository.save(carregamentoCashless);
    }

    @Override
    public double getSaldo(Long idEvento, String email_participante) {
        Evento evento = eventoRepository.findById(idEvento).orElseThrow(() -> new IllegalArgumentException("Evento with id " + idEvento + "not found"));
        Participante participante = participanteRepository.findByEmail(email_participante);
        ContaCashless contaCashless = contaCashlessRepository.findByParticipanteAndEvento(participante, evento);
        if(contaCashless == null || contaCashless.getId() == null) {
            return 0;
        }
        return contaCashless.getValorAtual();
    }

}