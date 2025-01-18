package pt.upskill.upfest.repositories.vendas;

import org.springframework.data.jpa.repository.JpaRepository;
import pt.upskill.upfest.entities.Evento;
import pt.upskill.upfest.entities.SerieBilhete;

import java.util.List;
import java.util.Optional;


public interface SerieBilheteRepository extends JpaRepository<SerieBilhete, Long> {
    List<SerieBilhete> findAllByEvento(Evento evento);
    Optional<SerieBilhete> findById (Long id);
}
