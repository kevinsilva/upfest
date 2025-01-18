package pt.upskill.upfest.repositories.vendas;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pt.upskill.upfest.entities.Bilhete;


@Repository
public interface ComprarBilheteRepository extends JpaRepository<Bilhete, Long> {
}
