package pt.upskill.upfest.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pt.upskill.upfest.entities.*;
import pt.upskill.upfest.models.ComprarBilheteModel;
import pt.upskill.upfest.models.PagamentoModel;
import pt.upskill.upfest.models.ValidarEntradaModel;
import pt.upskill.upfest.repositories.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
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
    @Autowired
    EntradaRepository entradaRepository;
    @Autowired
    ContaCashlessRepository contaCashlessRepository;

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
public Bilhete comprarBilhete(ComprarBilheteModel bilhete) {
    Evento evento = eventoRepository.findById(bilhete.getEvento())
            .orElseThrow(()->{throw new IllegalArgumentException("Evento Não Existe");});  //TESTED

    Participante participante = participanteRepository
            .findByEmail(bilhete.getEmail())
            .orElseGet(() -> {
                // Se nao encontra Participante, cria um novo
                Participante novoParticipante = new Participante();
                novoParticipante.setNome(bilhete.getNome());
                novoParticipante.setEmail(bilhete.getEmail());
                novoParticipante.setData_registo(LocalDateTime.now());
                return participanteRepository.save(novoParticipante);
            });
    Bilhete novoBilhete = new Bilhete();
    novoBilhete.setEvento(evento);
    novoBilhete.setParticipante(participante);

    novoBilhete.setSerieBilhete(serieBilheteRepository.findById(bilhete.getSerie())
            .orElseThrow(()->{throw new IllegalArgumentException("SerieBilhete Não Existe");}));
    novoBilhete.setCodigo("FOEMFOND");
    //novoBilhete.setCodigo(generateBilheteCodigo());  //para uma implementação real
    SerieBilhete serieBilhete = novoBilhete.getSerieBilhete();
    //Pagamento pagamento = new Pagamento(12345, 12345643, serieBilhete.getCusto());
    Pagamento pagamento = new Pagamento(this.gerarEntidade(participante), this.gerarReferencia(), serieBilhete.getCusto());
    pagamento.setData_compra(LocalDateTime.now());
    pagamento = pagamentoRepository.save(pagamento);
    novoBilhete.setPagamento(pagamento);

    ContaCashless conta = new ContaCashless();
    conta.setValorAtual(0.0);
    conta.setParticipante(participante);
    conta.setEvento(evento);
    contaCashlessRepository.save(conta);

    return bilheteRepository.save(novoBilhete);
}

//Gerar o codigo para uma implementação real
//    private String generateBilheteCodigo() {
//        return UUID.randomUUID().toString().substring(0,12);
//    }



    //2.3.pagamentos
    @Override
    public Pagamento validarPagamento(PagamentoModel info) {
        Pagamento pagamento = pagamentoRepository.findByReferenciaAndEntidade(info.getReferencia(), info.getEntidade())
                .orElseThrow(()->{throw new IllegalArgumentException("Pagamento não Existe");});
        if (pagamento.getData_validado() != null) { throw new IllegalArgumentException("Pagamento já Validado");}
        pagamento.setData_validado(LocalDateTime.now());
        return pagamentoRepository.save(pagamento);
    }

    @Override
    public Entrada validarEntrada(ValidarEntradaModel info) {

        Bilhete bilhete = bilheteRepository.findByCodigo(info.getCodigo())
                .orElseThrow(()->{throw new IllegalArgumentException("Não Existe Bilhete c/ codigo");});

        if (entradaRepository.findByBilhete(bilhete).isPresent()) {
            throw new IllegalArgumentException("Entrada Já Existe");}

        //Criar Entrada
        Entrada entrada = new Entrada();
        entrada.setBilhete(bilhete);
        entrada.setData(LocalDate.now());
        entrada.setHora(LocalTime.now());

        return entradaRepository.save(entrada);
    }

    public int gerarReferencia() {
        return Math.abs(UUID.randomUUID().hashCode() % 1000000000);
    }

    public int gerarEntidade(Participante participante) {
        String email = participante.getEmail();
        return Math.abs(email.hashCode());
    }
}
