package pt.upskill.upfest.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pt.upskill.upfest.entities.Artista;
import pt.upskill.upfest.entities.Evento;

@Repository
public interface ArtistaRepository extends JpaRepository<Artista, Long> {
}
