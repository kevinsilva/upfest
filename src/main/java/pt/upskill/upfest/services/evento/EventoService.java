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
    Evento createEvento(EventoModel info);
    Evento editEvento(Long id, EventoModel info);

    //Palco
    List<Palco> getAllPalcos(Evento evento);
    Palco createPalco(Long id, PalcoModel info);
    Palco editPalco(Long idEvento, PalcoModel info, Long idPalco);

    //Artista
    List<Artista> getAllArtistas(Evento evento);
    Artista createArtista(Long id, ArtistaModel info);
    Artista editArtista(Long idEvento, ArtistaModel info, Long idArtista);

    //Concerto
    List<Concerto> getAllConcertos(Evento evento);
    Concerto createConcerto(Long id, ConcertoModel info);
    Concerto editConcerto(Long idEvento, ConcertoModel info, Long idConcerto);

    //SerieBilhete
    List<SerieBilhete> getAllSerieBilhetes(Evento evento);
    SerieBilhete createSerieBilhete(Long id, SerieBilheteModel info);
    SerieBilhete editSerieBilhete(Long idEvento, SerieBilheteModel info, Long idSerie);
}
