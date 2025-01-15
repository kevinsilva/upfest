package pt.upskill.upfest.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pt.upskill.upfest.entities.Evento;
import pt.upskill.upfest.models.NovoEvento;
import pt.upskill.upfest.repositories.EventoRepository;

import java.util.List;

@Service
public class EventoServiceImpl implements EventoService {

    @Autowired
    EventoRepository eventoRepository;

    @Override
    public Evento getEvento(Long id) {
        Evento evento = eventoRepository.getById(id);
        return evento;
    }

    @Override
    public List<Evento> getEventos() {
        return eventoRepository.findAll();
    }

    @Override
    public Evento criarEvento(NovoEvento info) {
        Evento evento = new Evento();
        evento.setDesignacao(info.getDesignacao());
        return eventoRepository.save(evento);
    }

}
