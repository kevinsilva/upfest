package pt.upskill.upfest.models;

public class CompraModel {
    private String participante;
    private Long produto;
    private int quantidade;

    public String getParticipante() {
        return participante;
    }

    public void setParticipante(String emailParticipante) {
        this.participante = emailParticipante;
    }

    public Long getProduto() {
        return produto;
    }

    public void setProduto(Long produto) {
        this.produto = produto;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }
}
