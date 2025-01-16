package pt.upskill.upfest.models;

import java.time.LocalDate;

public class SerieBilheteModel {

    private String designacao;
    private int numero_bilhetes;
    private double custo;
    //@DateTimeFormat(pattern = "dd-MM-yyyy")
    private LocalDate limite_vendas;

    public String getDesignacao() {
        return designacao;
    }

    public void setDesignacao(String designacao) {
        this.designacao = designacao;
    }

    public int getNumero_bilhetes() {
        return numero_bilhetes;
    }

    public void setNumero_bilhetes(int numero_bilhetes) {
        this.numero_bilhetes = numero_bilhetes;
    }

    public double getCusto() {
        return custo;
    }

    public void setCusto(double custo) {
        this.custo = custo;
    }

    public LocalDate getLimite_vendas() {
        return limite_vendas;
    }

    public void setLimite_vendas(LocalDate limite_vendas) {
        this.limite_vendas = limite_vendas;
    }
}
