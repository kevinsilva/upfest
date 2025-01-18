package pt.upskill.upfest.services.evento;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pt.upskill.upfest.entities.*;
import pt.upskill.upfest.models.*;
import pt.upskill.upfest.repositories.evento.ArtistaRepository;
import pt.upskill.upfest.repositories.evento.ConcertoRepository;
import pt.upskill.upfest.repositories.evento.EventoRepository;
import pt.upskill.upfest.repositories.evento.PalcoRepository;
import pt.upskill.upfest.repositories.vendas.SerieBilheteRepository;

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
    @Autowired
    SerieBilheteRepository serieBilheteRepository;


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
            throw new RuntimeException("Artista " + id + " não existe.");
        }
        return opt.get();
    }

    @Override
    public Concerto getConcerto(Long id) {
        Optional<Concerto> opt = concertoRepository.findById(id);
        if(opt.isEmpty()) {
            throw new RuntimeException("Concerto " + id + " não existe.");
        }
        return opt.get();
    }

    @Override
    public SerieBilhete getSerieBilhete(Long id) {
        Optional<SerieBilhete> opt = serieBilheteRepository.findById(id);
        if(opt.isEmpty()) {
            throw new RuntimeException("Série de bilhetes " + id + " não existe.");
        }
        return opt.get();
    }


    //Evento
    @Override
    public List<Evento> getAllEventos() {
        return eventoRepository.findAll();
    }

    @Override
    public Evento criarEvento(EventoModel info) {
        Evento evento = new Evento();
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
        Evento evento = getEvento(idEvento);

        Palco palco = getPalco(idPalco);
        palco.setDesignacao(info.getDesignacao());
        palco.setEvento(evento);
        return palcoRepository.save(palco);
    }

    //Artista
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
        artista.setBiografia(info.getBiografia());
        artista.setEvento(evento);
        return artistaRepository.save(artista);
    }

    @Override
    public Artista editarArtista(Long idEvento, ArtistaModel info, Long idArtista) {
        Evento evento = getEvento(idEvento);

        Artista artista = getArtista(idArtista);
        artista.setNome(info.getNome());
        artista.setEstilo(info.getEstilo());
        artista.setBiografia(info.getBiografia());
        artista.setEvento(evento);
        return artistaRepository.save(artista);
    }

    //Concerto
    @Override
    public List<Concerto> getAllConcertos(Evento evento) {
        return concertoRepository.findAllByEvento(evento);
    }

    @Override
    public Concerto criarConcerto(Long id, ConcertoModel info) {
        Evento evento = getEvento(id);
        Artista artista = getArtista(info.getArtista());
        Palco palco = getPalco(info.getPalco());

        Concerto concerto = new Concerto();
        concerto.setData_hora_inicio(info.getData_hora_inicio());
        concerto.setData_hora_fim(info.getData_hora_fim());
        concerto.setEvento(evento);
        concerto.setArtista(artista);
        concerto.setPalco(palco);
        return concertoRepository.save(concerto);
    }

    @Override
    public Concerto editarConcerto(Long idEvento, ConcertoModel info, Long idConcerto) {
        Evento evento = getEvento(idEvento);
        Artista artista = getArtista(info.getArtista());
        Palco palco = getPalco(info.getPalco());

        Concerto concerto = getConcerto(idConcerto);
        concerto.setData_hora_inicio(info.getData_hora_inicio());
        concerto.setData_hora_fim(info.getData_hora_fim());
        concerto.setEvento(evento);
        concerto.setArtista(artista);
        concerto.setPalco(palco);
        return concertoRepository.save(concerto);
    }

    //Serie_Bilhete
    @Override
    public List<SerieBilhete> getAllSerieBilhetes(Evento evento) {
        return serieBilheteRepository.findAllByEvento(evento);
    }

    @Override
    public SerieBilhete criarSerieBilhete(Long id, SerieBilheteModel info) {
        Evento evento = getEvento(id);

        SerieBilhete serieBilhete = new SerieBilhete();
        serieBilhete.setDesignacao(info.getDesignacao());
        serieBilhete.setNumero_bilhetes(info.getNumero_bilhetes());
        serieBilhete.setLimite_vendas(info.getLimite_vendas());
        serieBilhete.setCusto(info.getCusto());
        serieBilhete.setEvento(evento);
        return serieBilheteRepository.save(serieBilhete);
    }

    @Override
    public SerieBilhete editarSerieBilhete(Long idEvento, SerieBilheteModel info, Long idSerie) {
        Evento evento = getEvento(idEvento);

        SerieBilhete serieBilhete = getSerieBilhete(idSerie);
        serieBilhete.setDesignacao(info.getDesignacao());
        serieBilhete.setNumero_bilhetes(info.getNumero_bilhetes());
        serieBilhete.setLimite_vendas(info.getLimite_vendas());
        serieBilhete.setCusto(info.getCusto());
        serieBilhete.setEvento(evento);
        return serieBilheteRepository.save(serieBilhete);
    }
}
