package pt.upskill.upfest.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pt.upskill.upfest.entities.Evento;
import pt.upskill.upfest.entities.Palco;
import pt.upskill.upfest.entities.SerieBilhete;

import java.util.List;


public interface SerieBilheteRepository extends JpaRepository<SerieBilhete, Long> {
    List<SerieBilhete> findAllByEvento(Evento evento);
}
