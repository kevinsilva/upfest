package pt.upskill.upfest.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Entity
public class Pagamento {
    @Id
    @GeneratedValue
    private Long id;
    private int entidade;
    private int referencia;
    private double valor;

    @DateTimeFormat(pattern = "dd-MM-yyyy HH:mm")
    private LocalDateTime data_compra;

    @DateTimeFormat(pattern = "dd-MM-yyyy HH:mm")
    private LocalDateTime data_validado;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getEntidade() {
        return entidade;
    }

    public void setEntidade(int entidade) {
        this.entidade = entidade;
    }

    public int getReferencia() {
        return referencia;
    }

    public void setReferencia(int referencia) {
        this.referencia = referencia;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public LocalDateTime getData_compra() {
        return data_compra;
    }

    public void setData_compra(LocalDateTime data_compra) {
        this.data_compra = data_compra;
    }

    public LocalDateTime getData_validado() {
        return data_validado;
    }

    public void setData_validado(LocalDateTime data_validado) {
        this.data_validado = data_validado;
    }
}
