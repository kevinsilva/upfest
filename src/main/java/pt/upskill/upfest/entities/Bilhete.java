package pt.upskill.upfest.entities;

import jakarta.persistence.*;

@Entity
public class Bilhete {
    @Id
    @GeneratedValue
    private Long id;
    @Column(unique = true)
    private String codigo;

    @ManyToOne
    private Participante participante;
    @ManyToOne
    private Evento evento;
    @ManyToOne
    private SerieBilhete serieBilhete;
    @ManyToOne
    private Pagamento pagamento;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public Participante getParticipante() {
        return participante;
    }

    public void setParticipante(Participante participante) {
        this.participante = participante;
    }

    public Evento getEvento() {
        return evento;
    }

    public void setEvento(Evento evento) {
        this.evento = evento;
    }

    public SerieBilhete getSerieBilhete() {
        return serieBilhete;
    }

    public void setSerieBilhete(SerieBilhete serieBilhete) {
        this.serieBilhete = serieBilhete;
    }

    public Pagamento getPagamento() {
        return pagamento;
    }

    public void setPagamento(Pagamento pagamento) {
        this.pagamento = pagamento;
    }
}
