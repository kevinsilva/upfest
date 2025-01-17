package pt.upskill.upfest.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class GastoCashless extends MovimentoCashless {
    @Id
    @GeneratedValue
    private Long id;
//    @ManyToOne
//    private MovimentoCashless movimentoCashless;
    @ManyToOne
    private ProdutoComerciante produtoComerciante;
    private int quantidade;
    private double valor_unitario;


//    public MovimentoCashless getMovimentoCashless() {
//        return movimentoCashless;
//    }
//
//    public void setMovimentoCashless(MovimentoCashless movimentoCashless) {
//        this.movimentoCashless = movimentoCashless;
//    }

    public ProdutoComerciante getProdutoComerciante() {
        return produtoComerciante;
    }

    public void setProdutoComerciante(ProdutoComerciante produtoComerciante) {
        this.produtoComerciante = produtoComerciante;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public double getValor_unitario() {
        return valor_unitario;
    }

    public void setValor_unitario(double valor_unitario) {
        this.valor_unitario = valor_unitario;
    }
}
