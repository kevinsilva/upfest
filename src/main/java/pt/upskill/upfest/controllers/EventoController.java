package pt.upskill.upfest.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import pt.upskill.upfest.entities.Evento;
import pt.upskill.upfest.models.NovoEvento;
import pt.upskill.upfest.services.EventoServiceImpl;

import java.util.List;

@RestController
public class EventoController {

    @Autowired
    EventoServiceImpl eventoService;

    @GetMapping(value = "/evento/listar")
    public List<Evento> getEventos(){
        return eventoService.getEventos();
    }

    @PostMapping(value = "/evento/criar")
    public Evento criarEvento(NovoEvento info){
        return eventoService.criarEvento(info);
    }

    //@PostMapping(value = "/evento/{id_evento}/editar")
}
