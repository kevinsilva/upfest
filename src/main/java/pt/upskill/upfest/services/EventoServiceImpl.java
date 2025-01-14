package pt.upskill.upfest.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pt.upskill.upfest.entities.Evento;
import pt.upskill.upfest.repositories.EventoRepository;

import java.util.List;

@Service
public class EventoServiceImpl implements EventoService {

    @Autowired
    EventoRepository eventoRepository;

    @Override
    public List<Evento> getEventos() {
        return eventoRepository.findAll();
    }
}
