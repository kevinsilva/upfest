package pt.upskill.upfest.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class PagamentoCashless extends Pagamento {

    @ManyToOne
    private ContaCashless contaCashless;

    public ContaCashless getContaCashless() {
        return contaCashless;
    }

    public void setContaCashless(ContaCashless contaCashless) {
        this.contaCashless = contaCashless;
    }
}
