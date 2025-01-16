package pt.upskill.upfest.models;

public class ArtistaModel {
    private String nome;
    private String estilo;
    private String biografia;

    public ArtistaModel(String nome, String estilo, String bibliografia) {
        this.nome = nome;
        this.estilo = estilo;
        this.biografia = bibliografia;
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

    public String getBiografia() {
        return biografia;
    }

    public void setBiografia(String biografia) {
        this.biografia = biografia;
    }
}
