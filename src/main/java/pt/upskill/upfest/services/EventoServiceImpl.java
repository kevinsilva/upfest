package pt.upskill.upfest.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pt.upskill.upfest.entities.Evento;
import pt.upskill.upfest.entities.Palco;
import pt.upskill.upfest.models.EventoModel;
import pt.upskill.upfest.models.PalcoModel;
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

}
