package pt.upskill.upfest.models;

public class ProdutoComercianteModel {
    private String designacao;
    private double valor;

    public ProdutoComercianteModel(String designacao, double valor) {
        this.designacao = designacao;
        this.valor = valor;
    }

    public String getDesignacao() {
        return designacao;
    }

    public void setDesignacao(String designacao) {
        this.designacao = designacao;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }
}
