package pt.upskill.upfest.services;

import pt.upskill.upfest.entities.Evento;
import pt.upskill.upfest.entities.Palco;
import pt.upskill.upfest.models.EventoModel;
import pt.upskill.upfest.models.PalcoModel;

import java.util.List;

public interface EventoService {

    //Evento
    Evento getEvento(Long id);

    Palco getPalco(Long id);

    List<Evento> getAllEventos();

    Evento criarEvento(EventoModel info);

    Evento editarEvento(Long id, EventoModel info);

    List<Palco> getAllPalcos(Evento evento);

    Palco criarPalco(Long id, PalcoModel info);

    Palco editarPalco(Long idEvento, PalcoModel info, Long idPalco);
}
