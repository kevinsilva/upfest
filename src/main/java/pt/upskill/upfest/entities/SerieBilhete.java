package pt.upskill.upfest.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
public class SerieBilhete {
    @Id
    @GeneratedValue
    private Long id;
    private String designacao;
    private int numero_bilhetes;
    private double custo;
    private LocalDate limite_vendas;

    @ManyToOne
    private Evento evento;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public Evento getEvento() {
        return evento;
    }

    public void setEvento(Evento evento) {
        this.evento = evento;
    }
}
