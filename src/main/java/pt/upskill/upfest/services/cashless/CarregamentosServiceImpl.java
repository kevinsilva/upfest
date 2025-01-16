package pt.upskill.upfest.services.cashless;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pt.upskill.upfest.entities.ContaCashless;
import pt.upskill.upfest.entities.Evento;
import pt.upskill.upfest.entities.Participante;
import pt.upskill.upfest.repositories.ContaCashlessRepository;
import pt.upskill.upfest.repositories.EventoRepository;
import pt.upskill.upfest.repositories.ParticipanteRepository;

@Service
public class CarregamentosServiceImpl implements CarregamentosService {

    @Autowired
    EventoRepository eventoRepository;
    @Autowired
    ParticipanteRepository participanteRepository;

    @Autowired
    ContaCashlessRepository contaCashlessRepository;

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