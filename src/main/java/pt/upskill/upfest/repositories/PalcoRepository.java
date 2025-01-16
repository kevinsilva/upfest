package pt.upskill.upfest.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pt.upskill.upfest.entities.Evento;
import pt.upskill.upfest.entities.Palco;

import java.util.List;

@Repository
public interface PalcoRepository extends JpaRepository<Palco, Long> {
    List<Palco> findAllByEvento(Evento evento);
}
