package pt.upskill.upfest.models;


public class PagamentoModel {
    private int entidade;
    private int referencia;
    private double valor;

    public PagamentoModel(int entidade, int referencia, double valor) {
        this.entidade = entidade;
        this.referencia = referencia;
        this.valor = valor;
    }

    public int getEntidade() {
        return entidade;
    }

    public void setEntidade(int entidade) {
        this.entidade = entidade;
    }

    public int getReferencia() {
        return referencia;
    }

    public void setReferencia(int referencia) {
        this.referencia = referencia;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }
}
