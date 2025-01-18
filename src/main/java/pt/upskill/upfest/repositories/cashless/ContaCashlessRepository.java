package pt.upskill.upfest.repositories.cashless;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pt.upskill.upfest.entities.ContaCashless;
import pt.upskill.upfest.entities.Evento;
import pt.upskill.upfest.entities.Participante;

@Repository
public interface ContaCashlessRepository extends JpaRepository<ContaCashless, Long> {

    ContaCashless findByParticipanteAndEvento(Participante participante, Evento evento);
}
