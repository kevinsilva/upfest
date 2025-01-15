package pt.upskill.upfest.services;

import pt.upskill.upfest.entities.Evento;
import pt.upskill.upfest.models.EventoModel;

import java.util.List;

public interface EventoService {

    Evento getEvento(Long id);

    List<Evento> getAllEventos();

    Evento criarEvento(EventoModel info);

    Evento editarEvento(Long id, EventoModel info);
}
