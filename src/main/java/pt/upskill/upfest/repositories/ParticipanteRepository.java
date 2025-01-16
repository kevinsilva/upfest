package pt.upskill.upfest.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pt.upskill.upfest.entities.Evento;
import pt.upskill.upfest.entities.Participante;

@Repository
public interface ParticipanteRepository extends JpaRepository<Participante, Long> {
    Participante findByEmail(String emailParticipante);
}
