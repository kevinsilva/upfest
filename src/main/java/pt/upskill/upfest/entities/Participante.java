package pt.upskill.upfest.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Entity
public class Participante {
    @Id
    @GeneratedValue
    private Long id;
    private String email;
    private String nome;
    @DateTimeFormat(pattern = "dd-MM-yyyy HH:mm")
    private LocalDateTime data_registo;

    public Participante(){}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public LocalDateTime getData_registo() {
        return data_registo;
    }

    public void setData_registo(LocalDateTime data_registo) {
        this.data_registo = data_registo;
    }

}
