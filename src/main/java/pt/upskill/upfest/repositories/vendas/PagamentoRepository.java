package pt.upskill.upfest.repositories.vendas;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pt.upskill.upfest.entities.Pagamento;
import pt.upskill.upfest.entities.PagamentoCashless;

import java.util.Optional;

@Repository
public interface PagamentoRepository extends JpaRepository<Pagamento, Long> {
    Optional<Pagamento> findByReferenciaAndEntidade(int referencia, int entidade);

    // Prevent casting errors
    @Query("SELECT pc FROM PagamentoCashless pc WHERE pc.referencia = :referencia AND pc.entidade = :entidade")
    Optional<PagamentoCashless> findByReferenciaAndEntidadeCashless(@Param("referencia") int referencia,
                                                                    @Param("entidade") int entidade);
}
