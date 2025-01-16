package pt.upskill.upfest.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pt.upskill.upfest.entities.Artista;
import pt.upskill.upfest.entities.Concerto;
import pt.upskill.upfest.entities.Evento;
import pt.upskill.upfest.entities.Palco;
import pt.upskill.upfest.models.ArtistaModel;
import pt.upskill.upfest.models.ConcertoModel;
import pt.upskill.upfest.models.EventoModel;
import pt.upskill.upfest.models.PalcoModel;
import pt.upskill.upfest.repositories.ArtistaRepository;
import pt.upskill.upfest.repositories.ConcertoRepository;
import pt.upskill.upfest.repositories.EventoRepository;
import pt.upskill.upfest.repositories.PalcoRepository;

import java.util.List;
import java.util.Optional;

@Service
public class EventoServiceImpl implements EventoService {

    @Autowired
    EventoRepository eventoRepository;
    @Autowired
    PalcoRepository palcoRepository;
    @Autowired
    ArtistaRepository artistaRepository;
    @Autowired
    ConcertoRepository concertoRepository;

    //Evento
    @Override
    public Evento getEvento(Long id) {
        Optional<Evento> opt = eventoRepository.findById(id);
        if(opt.isEmpty()) {
            throw new RuntimeException("Evento " + id + " não existe.");
        }
        return opt.get();
    }

    @Override
    public Palco getPalco(Long id) {
        Optional<Palco> opt = palcoRepository.findById(id);
        if(opt.isEmpty()) {
            throw new RuntimeException("Palco " + id + " não existe.");
        }
        return opt.get();
    }

    @Override
    public Artista getArtista(Long id) {
        Optional<Artista> opt = artistaRepository.findById(id);
        if(opt.isEmpty()) {
            throw new RuntimeException("Palco " + id + " não existe.");
        }
        return opt.get();
    }


    @Override
    public List<Evento> getAllEventos() {
        return eventoRepository.findAll();
    }

    @Override
    public Evento criarEvento(EventoModel info) {
        Evento evento = new Evento();
        System.out.println("AQUI: " + evento.getId());
        evento.setDesignacao(info.getDesignacao());
        return eventoRepository.save(evento);
    }

    @Override
    public Evento editarEvento(Long id, EventoModel info) {
        Evento evento = getEvento(id);

        evento.setDesignacao(info.getDesignacao());
        return eventoRepository.save(evento);
    }


    //Palco
    @Override
    public List<Palco> getAllPalcos(Evento evento) {
        return palcoRepository.findAllByEvento(evento);
    }

    @Override
    public Palco criarPalco(Long id, PalcoModel info) {
        Evento evento = getEvento(id);

        Palco palco = new Palco();
        palco.setDesignacao(info.getDesignacao());
        palco.setEvento(evento);
        return palcoRepository.save(palco);
    }

    @Override
    public Palco editarPalco(Long idEvento, PalcoModel info, Long idPalco) {
        Palco palco = getPalco(idPalco);
        palco.setDesignacao(info.getDesignacao());
        return palcoRepository.save(palco);
    }

    @Override
    public List<Artista> getAllArtistas(Evento evento) {
        return artistaRepository.findAllByEvento(evento);
    }

    @Override
    public Artista criarArtista(Long id, ArtistaModel info) {
        Evento evento = getEvento(id);

        Artista artista = new Artista();
        artista.setNome(info.getNome());
        artista.setEstilo(info.getEstilo());
        artista.setBiografia(info.getBibliografia());
        artista.setEvento(evento);
        return artistaRepository.save(artista);
    }

    @Override
    public Artista editarArtista(Long idEvento, ArtistaModel info, Long idArtista) {
        Artista artista = getArtista(idArtista);
        artista.setNome(info.getNome());
        return artistaRepository.save(artista);
    }

    @Override
    public List<Concerto> getAllConcertos(Evento evento) {
        return concertoRepository.findAllByEvento(evento);
    }

    @Override
    public Concerto criarConcerto(Long id, ConcertoModel info) {
        Evento evento = getEvento(id);

        Concerto concerto = new Concerto();
        concerto.setData_hora_inicio(info.getData_hora_inicio());
        concerto.setData_hora_fim(info.getData_hora_fim());
        concerto.setEvento(evento);
        return concertoRepository.save(concerto);
    }

}
