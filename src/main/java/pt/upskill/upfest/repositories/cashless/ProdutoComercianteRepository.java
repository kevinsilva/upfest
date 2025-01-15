package pt.upskill.upfest.repositories.cashless;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pt.upskill.upfest.entities.ProdutoComerciante;

@Repository
public interface ProdutoComercianteRepository extends JpaRepository<ProdutoComerciante, Long> {
}
