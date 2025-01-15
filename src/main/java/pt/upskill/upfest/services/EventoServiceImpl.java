package pt.upskill.upfest.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pt.upskill.upfest.entities.Evento;
import pt.upskill.upfest.models.EventoModel;
import pt.upskill.upfest.repositories.EventoRepository;

import java.util.List;

@Service
public class EventoServiceImpl implements EventoService {

    @Autowired
    EventoRepository eventoRepository;

    //Evento
    @Override
    public Evento getEvento(Long id) {
        Evento evento = eventoRepository.getById(id);
        return evento;
    }

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
        if (evento == null){
            throw new RuntimeException("Evento " + id + " não existe.");
        }
        evento.setDesignacao(info.getDesignacao());
        return eventoRepository.save(evento);
    }

}
