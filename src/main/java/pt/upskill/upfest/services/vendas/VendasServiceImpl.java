package pt.upskill.upfest.services.vendas;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pt.upskill.upfest.entities.*;
import pt.upskill.upfest.models.ComprarBilheteModel;
import pt.upskill.upfest.models.PagamentoModel;
import pt.upskill.upfest.models.ValidarEntradaModel;
import pt.upskill.upfest.repositories.cashless.ContaCashlessRepository;
import pt.upskill.upfest.repositories.evento.EventoRepository;
import pt.upskill.upfest.repositories.vendas.*;

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

    @Override
    public List<Participante> listParticipantes(Long id) {
        return participanteRepository.findAll();
    }

    @Override
    public List<Pagamento> listPagamentos(String participante) {
        return pagamentoRepository.findAll();
    }

    @Override
    public Bilhete buyBilhete(ComprarBilheteModel bilhete) {
        Evento evento = eventoRepository.findById(bilhete.getEvento())
                .orElseThrow(()->{throw new IllegalArgumentException("Evento não encontrado.");});

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
                .orElseThrow(()->{throw new IllegalArgumentException("SerieBilhete não encontrado.");}));
        novoBilhete.setCodigo(this.generateCodigoBilhete());

        SerieBilhete serieBilhete = novoBilhete.getSerieBilhete();

        Pagamento pagamento = new Pagamento(this.generateEntidade(participante), this.generateReferencia(), serieBilhete.getCusto());
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

    @Override
    public Pagamento validatePagamento(PagamentoModel info) {
        Pagamento pagamento = pagamentoRepository.findByReferenciaAndEntidade(info.getReferencia(), info.getEntidade())
                .orElseThrow(()->{throw new IllegalArgumentException("Pagamento não encontrado.");});
        if (pagamento.getData_validado() != null) { throw new IllegalArgumentException("Pagamento já validado.");}
        pagamento.setData_validado(LocalDateTime.now());
        return pagamentoRepository.save(pagamento);
    }

    @Override
    public Entrada validateEntrada(ValidarEntradaModel info) {
        Bilhete bilhete = bilheteRepository.findByCodigo(info.getCodigo())
                .orElseThrow(()->{throw new IllegalArgumentException("Não existe Bilhete com este código.");});

        if (entradaRepository.findByBilhete(bilhete).isPresent()) {
            throw new IllegalArgumentException("Entrada já existente.");
        }

        if(bilhete.getPagamento().getData_validado() == null) {
            throw new RuntimeException("O pagamento não foi validado.");
        }

        //Criar Entrada
        Entrada entrada = new Entrada();
        entrada.setBilhete(bilhete);
        entrada.setData(LocalDate.now());
        entrada.setHora(LocalTime.now());

        return entradaRepository.save(entrada);
    }

    public int generateReferencia() {
        if (pagamentoRepository.count() == 0) return 12345643;
        return Math.abs(UUID.randomUUID().hashCode() % 1000000000);
    }

    public int generateEntidade(Participante participante) {
        if (pagamentoRepository.count() == 0) return 12345;
        String email = participante.getEmail();
        return Math.abs(email.hashCode());
    }

    private String generateCodigoBilhete() {
        if (bilheteRepository.count() == 0) return "FOEMFOND";
        return UUID.randomUUID().toString().substring(0,12);
    }
}
