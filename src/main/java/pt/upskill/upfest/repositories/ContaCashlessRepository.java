package pt.upskill.upfest.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pt.upskill.upfest.entities.ContaCashless;

import java.util.Optional;

public interface ContaCashlessRepository extends JpaRepository<ContaCashless, Long> {
    Optional<ContaCashless> findByParticipanteId(Long participanteId);
}


