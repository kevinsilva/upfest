package pt.upskill.upfest.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pt.upskill.upfest.entities.Evento;
import pt.upskill.upfest.models.EventoModel;
import pt.upskill.upfest.services.EventoServiceImpl;

import java.util.List;

@RestController
public class EventoController {

    @Autowired
    EventoServiceImpl eventoService;

    //Evento
    @GetMapping(value = "/evento/listar")
    public List<Evento> getAllEventos(){
        return eventoService.getAllEventos();
    }

    @PostMapping(value = "/evento/criar")
    public Evento criarEvento(EventoModel info){
        return eventoService.criarEvento(info);
    }

    @PostMapping(value = "/evento/{id_evento}/editar")
    public Evento editarEvento(@PathVariable ("id_evento") Long id, @ModelAttribute EventoModel info) {
        return eventoService.editarEvento(id, info);
    }
}
