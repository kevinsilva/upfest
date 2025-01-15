package pt.upskill.upfest.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class PagamentoCashless {

    @Id
    @ManyToOne
    private Pagamento pagamento;
    @ManyToOne
    private ContaCashless contaCashless;


    public Pagamento getPagamento() {
        return pagamento;
    }

    public void setPagamento(Pagamento pagamento) {
        this.pagamento = pagamento;
    }

    public ContaCashless getContaCashless() {
        return contaCashless;
    }

    public void setContaCashless(ContaCashless contaCashless) {
        this.contaCashless = contaCashless;
    }
}
