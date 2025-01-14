package pt.upskill.upfest.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Entity
public class MovimentoCashless {
    @Id
    @GeneratedValue
    private Long id;
    private double valor;
    private double saldo;

    // private enum tipo {valor1, valor2;}

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
}
