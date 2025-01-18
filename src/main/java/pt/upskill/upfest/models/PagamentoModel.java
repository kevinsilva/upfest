package pt.upskill.upfest.models;


import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

public class PagamentoModel {
    private int entidade;
    private int referencia;
    private double valor;
    @DateTimeFormat(pattern = "dd-MM-yyyy HH:mm")
    private LocalDateTime data_compra;
    @DateTimeFormat(pattern = "dd-MM-yyyy HH:mm")
    private LocalDateTime data_validado;


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
}
