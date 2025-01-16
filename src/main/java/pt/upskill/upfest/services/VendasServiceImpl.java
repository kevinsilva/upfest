package pt.upskill.upfest.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pt.upskill.upfest.entities.*;
import pt.upskill.upfest.models.ComprarBilheteModel;
import pt.upskill.upfest.models.PagamentoModel;
import pt.upskill.upfest.repositories.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class VendasServiceImpl implements VendasService {

    @Autowired
    PagamentoRepository pagamentoRepository;
    @Autowired
    ParticipanteRepository participanteRepository;
    @Autowired
    EventoRepository eventoRepository;
    @Autowired
    ComprarBilheteRepository comprarBilheteRepository;
    @Autowired
    BilheteRepository bilheteRepository;
    @Autowired
    SerieBilheteRepository serieBilheteRepository;

//    @Override
//    public List<Participante> listarParticipantes(Long evento) {
//        List<Bilhete> bilhetes = bilheteRepository.findByEventoId(evento);
//        return bilhetes.stream().map(Bilhete::getParticipante).toList();
//    }

//    @Override
//    public List<Pagamento> listarPagamentos(String participante) {
//        List<Bilhete> bilhetes = bilheteRepository.findByParticipanteEmail(participante);
//        return bilhetes.stream().map(Bilhete::getPagamento).toList();
//    }

    @Override
    public List<Participante> listarParticipantes(Long id) {
        return participanteRepository.findAll();
    }

    @Override
    public List<Pagamento> listarPagamentos(String participante) {
        return pagamentoRepository.findAll();
    }


    //2.1.bilhete
    @Override
    public Participante getParticipante(Long id) {
        Optional<Participante> opt = participanteRepository.findById(id);
        if(opt.isEmpty()) {
            throw new RuntimeException("Participante " + id + " não existe.");
        }
        return opt.get();
    }

    @Override
    public Bilhete comprarBilhete(ComprarBilheteModel bilheteModel) {
        Evento evento = eventoRepository.getById(bilheteModel.getEvento());

        Participante participante = participanteRepository.findByEmail(bilheteModel.getEmail());
        if(participante == null) {
            // Se nao encontra Participante, cria um novo
            Participante novoParticipante = new Participante();
            novoParticipante.setNome(bilheteModel.getNome());
            novoParticipante.setEmail(bilheteModel.getEmail());
            novoParticipante.setData_registo(LocalDateTime.now());
            participanteRepository.save(novoParticipante);
        };

        Bilhete novoBilhete = new Bilhete();
        novoBilhete.setEvento(evento);
        novoBilhete.setParticipante(participante);
        novoBilhete.setSerieBilhete(serieBilheteRepository.getReferenceById(bilheteModel.getSerie()));
        novoBilhete.setCodigo(generateBilheteCodigo());
        novoBilhete.setPagamento(null);
        return bilheteRepository.save(novoBilhete);
    }

    private String generateBilheteCodigo() {
        return UUID.randomUUID().toString().substring(0,12);
    }



    //2.3.pagamentos
    @Override
    public Pagamento validarPagamento(PagamentoModel info) {
        Pagamento pagamento = new Pagamento();
        pagamento.setEntidade(info.getEntidade());
        pagamento.setReferencia(info.getReferencia());
        pagamento.setValor(info.getValor());
        pagamento.setData_validado(LocalDateTime.now());
        return pagamentoRepository.save(pagamento);
    }




}
