package pt.upskill.upfest.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pt.upskill.upfest.entities.Bilhete;

import java.util.List;
import java.util.Optional;

@Repository
public interface BilheteRepository extends JpaRepository <Bilhete, Long> {

    Optional<Bilhete> findById (Long Id);

    List<Bilhete> findByEventoId(Long eventoId);

    List<Bilhete> findByParticipanteEmail(String email);

}
