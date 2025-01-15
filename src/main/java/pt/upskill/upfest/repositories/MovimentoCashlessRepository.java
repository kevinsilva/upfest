package pt.upskill.upfest.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pt.upskill.upfest.entities.MovimentoCashless;

import java.util.List;

public interface MovimentoCashlessRepository extends JpaRepository<MovimentoCashless, Long> {
    List<MovimentoCashless> findByContaCashlessEventoId(Long eventoId);
    List<MovimentoCashless> findByContaCashlessEventoIdAndContaCashlessParticipanteEmail(Long eventoId, String email);
}




