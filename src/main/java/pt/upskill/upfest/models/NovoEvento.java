package pt.upskill.upfest.models;

public class NovoEvento {
    private String designacao;

    public NovoEvento(String designacao) {
        this.designacao = designacao;
    }

    public String getDesignacao() {
        return designacao;
    }

    public void setDesignacao(String designacao) {
        this.designacao = designacao;
    }
}
