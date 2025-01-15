package pt.upskill.upfest.models;

public class EventoModel {
    private String designacao;

    public EventoModel(String designacao) {
        this.designacao = designacao;
    }

    public String getDesignacao() {
        return designacao;
    }

    public void setDesignacao(String designacao) {
        this.designacao = designacao;
    }
}
