package pt.upskill.upfest.repositories.cashless;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pt.upskill.upfest.entities.Comerciante;

@Repository
public interface ComercianteRepository extends JpaRepository<Comerciante, Long> {
}
