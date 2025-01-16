package pt.upskill.upfest.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pt.upskill.upfest.entities.Evento;
import pt.upskill.upfest.entities.Palco;
import pt.upskill.upfest.models.EventoModel;
import pt.upskill.upfest.models.PalcoModel;
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
    public Evento criarEvento(@RequestBody EventoModel info){
        return eventoService.criarEvento(info);
    }

    @PostMapping(value = "/evento/{id_evento}/editar")
    public Evento editarEvento(@PathVariable ("id_evento") Long id, @RequestBody EventoModel info) {
        return eventoService.editarEvento(id, info);
    }

    //Palco
    @GetMapping(value = "/evento/{id_evento}/palco/listar")
    public List<Palco> getAllPalcos(@ModelAttribute ("id_evento") Evento evento){
        return eventoService.getAllPalcos(evento);
    }

    @PostMapping(value = "/evento/{id_evento}/palco/criar")
    public Palco criarPalco(@PathVariable ("id_evento") Long id, @RequestBody PalcoModel info){
        return eventoService.criarPalco(id, info);
    }

    @PostMapping(value = "/evento/{id_evento}/palco/{id_palco}/editar")
    public Palco editarPalco(@PathVariable ("id_evento") Long idEvento, @RequestBody PalcoModel info, @PathVariable("id_palco") Long idPalco) {
        return eventoService.editarPalco(idEvento, info, idEvento);
    }
}
