package pt.upskill.upfest.models;

public class CriarEvento {
    private String designacao;

    public CriarEvento(String designacao) {
        this.designacao = designacao;
    }

    public String getDesignacao() {
        return designacao;
    }

    public void setDesignacao(String designacao) {
        this.designacao = designacao;
    }
}
