package pt.upskill.upfest.repositories.vendas;

import org.springframework.data.jpa.repository.JpaRepository;
import pt.upskill.upfest.entities.Bilhete;
import pt.upskill.upfest.entities.Entrada;

import java.util.Optional;

public interface EntradaRepository extends JpaRepository<Entrada, Long> {
    Optional<Entrada> findByBilhete (Bilhete bilhete);
}
