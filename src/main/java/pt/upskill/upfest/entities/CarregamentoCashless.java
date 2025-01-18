package pt.upskill.upfest.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class CarregamentoCashless extends MovimentoCashless {

    @ManyToOne
    private PagamentoCashless pagamentoCashless;


    public PagamentoCashless getPagamentoCashless() {
        return pagamentoCashless;
    }

    public void setPagamentoCashless(PagamentoCashless pagamentoCashless) {
        this.pagamentoCashless = pagamentoCashless;
    }
}
