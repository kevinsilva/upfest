package pt.upskill.upfest.repositories.evento;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pt.upskill.upfest.entities.Concerto;
import pt.upskill.upfest.entities.Evento;

import java.util.List;

@Repository
public interface ConcertoRepository extends JpaRepository<Concerto, Long> {
    List<Concerto> findAllByEvento(Evento evento);
}
