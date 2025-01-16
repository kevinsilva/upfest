package pt.upskill.upfest.repositories.cashless;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pt.upskill.upfest.entities.Comerciante;
import pt.upskill.upfest.entities.ProdutoComerciante;

import java.util.List;

@Repository
public interface ProdutoComercianteRepository extends JpaRepository<ProdutoComerciante, Long> {
    List<ProdutoComerciante> findByComerciante(Comerciante comerciante);
}
