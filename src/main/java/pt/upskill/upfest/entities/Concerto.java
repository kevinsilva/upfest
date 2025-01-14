package pt.upskill.upfest.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.util.Date;

@Entity
public class Concerto {

    @Id
    @GeneratedValue
    private Long id;

    @DateTimeFormat(pattern = "dd-MM-yyyy HH:mm")
    private LocalDateTime data_hora_inicio;
    @DateTimeFormat(pattern = "dd-MM-yyyy HH:mm")
    private LocalDateTime data_hora_fim;

    @ManyToOne
    private Evento evento;
    @ManyToOne
    private Artista artista;
    @ManyToOne
    private Palco palco;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Evento getEvento() {
        return evento;
    }

    public void setEvento(Evento evento) {
        this.evento = evento;
    }

    public Artista getArtista() {
        return artista;
    }

    public void setArtista(Artista artista) {
        this.artista = artista;
    }

    public Palco getPalco() {
        return palco;
    }

    public void setPalco(Palco palco) {
        this.palco = palco;
    }
}
