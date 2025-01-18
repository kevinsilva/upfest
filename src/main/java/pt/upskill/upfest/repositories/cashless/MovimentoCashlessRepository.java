package pt.upskill.upfest.repositories.cashless;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pt.upskill.upfest.entities.ContaCashless;
import pt.upskill.upfest.entities.MovimentoCashless;

@Repository
public interface MovimentoCashlessRepository extends JpaRepository<MovimentoCashless, Long> {
    Iterable<MovimentoCashless> findByContaCashless(ContaCashless contaCashless);
}
