package pt.upskill.upfest.controllers.evento;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pt.upskill.upfest.entities.*;
import pt.upskill.upfest.models.*;
import pt.upskill.upfest.services.evento.EventoServiceImpl;

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
    public Evento createEvento(@RequestBody EventoModel info){
        return eventoService.createEvento(info);
    }

    @PostMapping(value = "/evento/{id_evento}/editar")
    public Evento editEvento(@PathVariable ("id_evento") Long id, @RequestBody EventoModel info) {
        return eventoService.editEvento(id, info);
    }

    //Palco
    @GetMapping(value = "/evento/{id_evento}/palco/listar")
    public List<Palco> getAllPalcos(@ModelAttribute ("id_evento") Evento evento){
        return eventoService.getAllPalcos(evento);
    }

    @PostMapping(value = "/evento/{id_evento}/palco/criar")
    public Palco createPalco(@PathVariable ("id_evento") Long id, @RequestBody PalcoModel info){
        return eventoService.createPalco(id, info);
    }

    @PostMapping(value = "/evento/{id_evento}/palco/{id_palco}/editar")
    public Palco editPalco(@PathVariable ("id_evento") Long idEvento, @RequestBody PalcoModel info, @PathVariable("id_palco") Long idPalco) {
        return eventoService.editPalco(idEvento, info, idPalco);
    }

    //Artista
    @GetMapping(value = "/evento/{id_evento}/artistas/listar")
    public List<Artista> getAllArtistas(@ModelAttribute ("id_evento") Evento evento) {
        return eventoService.getAllArtistas(evento);
    }

    @PostMapping(value = "/evento/{id_evento}/artistas/criar")
    public Artista createArtista(@PathVariable ("id_evento") Long id, @RequestBody ArtistaModel info){
        return eventoService.createArtista(id, info);
    }

    @PostMapping(value = "/evento/{id_evento}/artistas/{id_artista}/editar")
    public Artista editArtista(@PathVariable ("id_evento") Long idEvento, @RequestBody ArtistaModel info, @PathVariable("id_artista") Long idArtista) {
        return eventoService.editArtista(idEvento, info, idArtista);
    }

    //Concerto
    @GetMapping(value = "/evento/{id_evento}/concertos/listar")
    public List<Concerto> getAllConcertos(@ModelAttribute ("id_evento") Evento evento) {
        return eventoService.getAllConcertos(evento);
    }

    @PostMapping(value = "/evento/{id_evento}/concertos/criar")
    public Concerto createConcerto(@PathVariable ("id_evento") Long id, @RequestBody ConcertoModel info){
        return eventoService.createConcerto(id, info);
    }

    @PostMapping(value = "/evento/{id_evento}/concertos/{id_concerto}/editar")
    public Concerto editConcerto(@PathVariable ("id_evento") Long idEvento, @RequestBody ConcertoModel info, @PathVariable("id_concerto") Long idConcerto) {
        return eventoService.editConcerto(idEvento, info, idConcerto);
    }

    //Serie_Bilhete
    @GetMapping(value = "/evento/{id_evento}/series_bilhetes/listar")
    public List<SerieBilhete> getAllSerieBilhetes(@ModelAttribute ("id_evento") Evento evento) {
        return eventoService.getAllSerieBilhetes(evento);
    }

    @PostMapping(value = "/evento/{id_evento}/series_bilhetes/criar")
    public SerieBilhete createSerieBilhete(@PathVariable ("id_evento") Long id, @RequestBody SerieBilheteModel info){
        return eventoService.createSerieBilhete(id, info);
    }

    @PostMapping(value = "/evento/{id_evento}/series_bilhetes/{id_serie}/editar")
    public SerieBilhete editSerieBilhete(@PathVariable ("id_evento") Long idEvento, @RequestBody SerieBilheteModel info, @PathVariable("id_serie") Long idSerie) {
        return eventoService.editSerieBilhete(idEvento, info, idSerie);
    }
}
