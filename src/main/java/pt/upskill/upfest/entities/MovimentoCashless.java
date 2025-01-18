package pt.upskill.upfest.entities;

import jakarta.persistence.*;
import org.springframework.format.annotation.DateTimeFormat;
import pt.upskill.upfest.enums.TipoMovimento;

import java.time.LocalDateTime;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class MovimentoCashless {
    @Id
    @GeneratedValue
    private Long id;
    private double valor;
    private double saldo;

    @Enumerated(EnumType.STRING)
    private TipoMovimento tipoMovimento;

    @DateTimeFormat(pattern = "dd-MM-yyyy HH:mm")
    private LocalDateTime data;

    @ManyToOne
    private ContaCashless contaCashless;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    public LocalDateTime getData() {
        return data;
    }

    public void setData(LocalDateTime data) {
        this.data = data;
    }

    public ContaCashless getContaCashless() {
        return contaCashless;
    }

    public void setContaCashless(ContaCashless contaCashless) {
        this.contaCashless = contaCashless;
    }

    public TipoMovimento getTipoMovimento() {
        return tipoMovimento;
    }

    public void setTipoMovimento(TipoMovimento tipoMovimento) {
        this.tipoMovimento = tipoMovimento;
    }
}
