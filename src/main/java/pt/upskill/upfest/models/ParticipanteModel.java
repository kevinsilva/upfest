package pt.upskill.upfest.models;


import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

public class ParticipanteModel {
    private Long id;
    private String nome;
    private String email;
    @DateTimeFormat(pattern = "dd-MM-yyyy HH:mm")
    private LocalDateTime data_registo;

    public ParticipanteModel(Long id, String nome, String email, LocalDateTime data_registo) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.data_registo = data_registo;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getData_registo() {
        return data_registo;
    }

    public void setData_registo(LocalDateTime data_registo) {
        this.data_registo = data_registo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
