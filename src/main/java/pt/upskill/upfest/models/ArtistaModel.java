package pt.upskill.upfest.models;

public class ArtistaModel {
    private String nome;
    private String estilo;
    private String bibliografia;

    public ArtistaModel(String nome, String estilo, String bibliografia) {
        this.nome = nome;
        this.estilo = estilo;
        this.bibliografia = bibliografia;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEstilo() {
        return estilo;
    }

    public void setEstilo(String estilo) {
        this.estilo = estilo;
    }

    public String getBibliografia() {
        return bibliografia;
    }

    public void setBibliografia(String bibliografia) {
        this.bibliografia = bibliografia;
    }
}
