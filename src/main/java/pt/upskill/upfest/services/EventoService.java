package pt.upskill.upfest.services;

import pt.upskill.upfest.entities.Evento;
import pt.upskill.upfest.models.NovoEvento;

import java.util.List;

public interface EventoService {

    Evento getEvento(Long id);

    List<Evento> getEventos();

    Evento criarEvento(NovoEvento info);
}
