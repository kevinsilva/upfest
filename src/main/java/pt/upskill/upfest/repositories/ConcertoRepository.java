package pt.upskill.upfest.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pt.upskill.upfest.entities.Concerto;
import pt.upskill.upfest.entities.Evento;

@Repository
public interface ConcertoRepository extends JpaRepository<Concerto, Long> {
}
