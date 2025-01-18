package pt.upskill.upfest.repositories.evento;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pt.upskill.upfest.entities.Evento;

@Repository
public interface EventoRepository extends JpaRepository<Evento, Long> {
}
