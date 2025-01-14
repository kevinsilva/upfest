package pt.upskill.upfest.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import pt.upskill.upfest.entities.Evento;
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
}
