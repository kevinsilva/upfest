package pt.upskill.upfest.models;

import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

public class ConcertoModel {

    @DateTimeFormat(pattern = "dd-MM-yyyy HH:mm")
    private LocalDateTime data_hora_inicio;
    @DateTimeFormat(pattern = "dd-MM-yyyy HH:mm")
    private LocalDateTime data_hora_fim;

    public ConcertoModel(LocalDateTime data_hora_inicio, LocalDateTime data_hora_fim) {
        this.data_hora_inicio = data_hora_inicio;
        this.data_hora_fim = data_hora_fim;
    }

    public LocalDateTime getData_hora_inicio() {
        return data_hora_inicio;
    }

    public void setData_hora_inicio(LocalDateTime data_hora_inicio) {
        this.data_hora_inicio = data_hora_inicio;
    }

    public LocalDateTime getData_hora_fim() {
        return data_hora_fim;
    }

    public void setData_hora_fim(LocalDateTime data_hora_fim) {
        this.data_hora_fim = data_hora_fim;
    }
}
