package pt.upskill.upfest.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pt.upskill.upfest.entities.Artista;
import pt.upskill.upfest.entities.Evento;

import java.util.List;

@Repository
public interface ArtistaRepository extends JpaRepository<Artista, Long> {
    List<Artista> findAllByEvento(Evento evento);
}
