package pt.upskill.upfest.services.cashless.comerciante;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pt.upskill.upfest.entities.Comerciante;
import pt.upskill.upfest.entities.Evento;
import pt.upskill.upfest.models.ComercianteModel;
import pt.upskill.upfest.repositories.evento.EventoRepository;
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
    public List<Comerciante> listComerciantes(Long idEvento) {
        Evento evento = eventoRepository.findById(idEvento)
                .orElseThrow(() -> new IllegalArgumentException("Evento com id " + idEvento + " não encontrado."));

        return comercianteRepository.findByEvento(evento);
    }

    @Override
    public Comerciante createComerciante(Long idEvento, ComercianteModel comercianteModel) {
        Evento evento = eventoRepository.findById(idEvento)
                .orElseThrow(() -> new IllegalArgumentException("Evento com id " + idEvento + " não encontrado."));

        Comerciante comerciante = new Comerciante();
        comerciante.setDesignacao(comercianteModel.getDesignacao());
        comerciante.setEvento(evento);

        return comercianteRepository.save(comerciante);
    }

    @Override
    public Comerciante editComerciante(Long idEvento, Long idComerciante, ComercianteModel comercianteModel) {
        Comerciante comerciante = comercianteRepository.findById(idComerciante).orElseThrow(() ->
                new IllegalArgumentException("Comerciante com id " + idComerciante + " não encontrado."));

        if(!comerciante.getEvento().getId().equals(idEvento)){
            throw new IllegalArgumentException("O comerciante não faz parte deste evento.");
        }

        comerciante.setDesignacao(comercianteModel.getDesignacao());

        return comercianteRepository.save(comerciante);
    }
}
