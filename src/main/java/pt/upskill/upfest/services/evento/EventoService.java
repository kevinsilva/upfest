package pt.upskill.upfest.services.evento;

import pt.upskill.upfest.entities.*;
import pt.upskill.upfest.models.*;

import java.util.List;

public interface EventoService {

    Evento getEvento(Long id);

    Palco getPalco(Long id);

    Artista getArtista(Long id);

    Concerto getConcerto(Long id);

    SerieBilhete getSerieBilhete(Long id);

    //Evento
    List<Evento> getAllEventos();

    Evento criarEvento(EventoModel info);

    Evento editarEvento(Long id, EventoModel info);

    //Palco
    List<Palco> getAllPalcos(Evento evento);

    Palco criarPalco(Long id, PalcoModel info);

    Palco editarPalco(Long idEvento, PalcoModel info, Long idPalco);

    //Artista
    List<Artista> getAllArtistas(Evento evento);

    Artista criarArtista(Long id, ArtistaModel info);

    Artista editarArtista(Long idEvento, ArtistaModel info, Long idArtista);

    //Concerto
    List<Concerto> getAllConcertos(Evento evento);

    Concerto criarConcerto(Long id, ConcertoModel info);

    Concerto editarConcerto(Long idEvento, ConcertoModel info, Long idConcerto);

    //SerieBilhete
    List<SerieBilhete> getAllSerieBilhetes(Evento evento);

    SerieBilhete criarSerieBilhete(Long id, SerieBilheteModel info);

    SerieBilhete editarSerieBilhete(Long idEvento, SerieBilheteModel info, Long idSerie);
}
