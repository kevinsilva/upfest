package pt.upskill.upfest.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class CarregamentoCashless {

    @Id
    @ManyToOne
    private MovimentoCashless movimentoCashless;
    @ManyToOne
    private PagamentoCashless pagamentoCashless;


    public MovimentoCashless getMovimentoCashless() {
        return movimentoCashless;
    }

    public void setMovimentoCashless(MovimentoCashless movimentoCashless) {
        this.movimentoCashless = movimentoCashless;
    }

    public PagamentoCashless getPagamentoCashless() {
        return pagamentoCashless;
    }

    public void setPagamentoCashless(PagamentoCashless pagamentoCashless) {
        this.pagamentoCashless = pagamentoCashless;
    }
}
