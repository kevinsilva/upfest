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
        return eventoService.editarPalco(idEvento, info, idPalco);
    }

    //Artista
    @GetMapping(value = "/evento/{id_evento}/artistas/listar")
    public List<Artista> getAllArtistas(@ModelAttribute ("id_evento") Evento evento) {
        return eventoService.getAllArtistas(evento);
    }

    @PostMapping(value = "/evento/{id_evento}/artistas/criar")
    public Artista criarArtista(@PathVariable ("id_evento") Long id, @RequestBody ArtistaModel info){
        return eventoService.criarArtista(id, info);
    }

    @PostMapping(value = "/evento/{id_evento}/artistas/{id_artista}/editar")
    public Artista editarArtista(@PathVariable ("id_evento") Long idEvento, @RequestBody ArtistaModel info, @PathVariable("id_artista") Long idArtista) {
        return eventoService.editarArtista(idEvento, info, idArtista);
    }

    //Concerto
    @GetMapping(value = "/evento/{id_evento}/concertos/listar")
    public List<Concerto> getAllConcertos(@ModelAttribute ("id_evento") Evento evento) {
        return eventoService.getAllConcertos(evento);
    }

    @PostMapping(value = "/evento/{id_evento}/concertos/criar")
    public Concerto criarConcerto(@PathVariable ("id_evento") Long id, @RequestBody ConcertoModel info){
        return eventoService.criarConcerto(id, info);
    }

    @PostMapping(value = "/evento/{id_evento}/concertos/{id_concerto}/editar")
    public Concerto editarConcerto(@PathVariable ("id_evento") Long idEvento, @RequestBody ConcertoModel info, @PathVariable("id_concerto") Long idConcerto) {
        return eventoService.editarConcerto(idEvento, info, idConcerto);
    }

    //Serie_Bilhete
    @GetMapping(value = "/evento/{id_evento}/series_bilhetes/listar")
    public List<SerieBilhete> getAllSerieBilhetes(@ModelAttribute ("id_evento") Evento evento) {
        return eventoService.getAllSerieBilhetes(evento);
    }

    @PostMapping(value = "/evento/{id_evento}/series_bilhetes/criar")
    public SerieBilhete criarSerieBilhete(@PathVariable ("id_evento") Long id, @RequestBody SerieBilheteModel info){
        return eventoService.criarSerieBilhete(id, info);
    }

    @PostMapping(value = "/evento/{id_evento}/series_bilhetes/{id_serie}/editar")
    public SerieBilhete editarSerieBilhete(@PathVariable ("id_evento") Long idEvento, @RequestBody SerieBilheteModel info, @PathVariable("id_serie") Long idSerie) {
        return eventoService.editarSerieBilhete(idEvento, info, idSerie);
    }
}
