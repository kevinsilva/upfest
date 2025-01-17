package pt.upskill.upfest.services.cashless.comerciante;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pt.upskill.upfest.entities.Comerciante;
import pt.upskill.upfest.entities.Evento;
import pt.upskill.upfest.models.ComercianteModel;
import pt.upskill.upfest.repositories.EventoRepository;
import pt.upskill.upfest.repositories.cashless.ComercianteRepository;
import pt.upskill.upfest.repositories.cashless.ProdutoComercianteRepository;

import java.util.List;

@Service
public class ComercianteServiceImpl implements ComercianteService {
    @Autowired
    ProdutoComercianteRepository produtoComercianteRepository;
    @Autowired
    ComercianteRepository comercianteRepository;
    @Autowired
    EventoRepository eventoRepository;

    @Override
        public List<Comerciante> listarComerciantes(Long idEvento) {
        Evento evento = eventoRepository.findById(idEvento)
                .orElseThrow(() -> new IllegalArgumentException("Evento with id " + idEvento + " not found"));

        return comercianteRepository.findByEvento(evento);
        }

    @Override
    public Comerciante criarComerciante(Long idEvento, ComercianteModel comercianteModel) {
        Evento evento = eventoRepository.findById(idEvento)
                .orElseThrow(() -> new IllegalArgumentException("Evento with id " + idEvento + " not found"));
    
        Comerciante comerciante = new Comerciante();
        comerciante.setDesignacao(comercianteModel.getDesignacao());
        comerciante.setEvento(evento);
    
        return comercianteRepository.save(comerciante);
    }

    @Override
    public Comerciante editarComerciante(Long idEvento, Long idComerciante, ComercianteModel comercianteModel) {
        Comerciante comerciante = comercianteRepository.findById(idComerciante).orElseThrow(() -> new IllegalArgumentException("Comerciante with id " + idComerciante + " not found"));

        if(!comerciante.getEvento().getId().equals(idEvento)){
            throw new IllegalArgumentException("Comerciante is not part of this event");
        }

        comerciante.setDesignacao(comercianteModel.getDesignacao());

        return comercianteRepository.save(comerciante);
    }
}
