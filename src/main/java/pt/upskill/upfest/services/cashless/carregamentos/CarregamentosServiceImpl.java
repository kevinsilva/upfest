package pt.upskill.upfest.services.cashless.carregamentos;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pt.upskill.upfest.entities.*;
import pt.upskill.upfest.enums.TipoMovimento;
import pt.upskill.upfest.models.CarregamentoModel;
import pt.upskill.upfest.models.ValidarPagamentoModel;
import pt.upskill.upfest.repositories.cashless.ContaCashlessRepository;
import pt.upskill.upfest.repositories.cashless.MovimentoCashlessRepository;
import pt.upskill.upfest.repositories.evento.EventoRepository;
import pt.upskill.upfest.repositories.vendas.PagamentoRepository;
import pt.upskill.upfest.repositories.vendas.ParticipanteRepository;
import pt.upskill.upfest.services.vendas.VendasService;

import java.time.LocalDateTime;
import java.util.Random;

@Service
public class CarregamentosServiceImpl implements CarregamentosService {

    @Autowired
    EventoRepository eventoRepository;
    @Autowired
    ParticipanteRepository participanteRepository;
    @Autowired
    ContaCashlessRepository contaCashlessRepository;
    @Autowired
    MovimentoCashlessRepository movimentoCashlessRepository;
    @Autowired
    PagamentoRepository pagamentoRepository;
    @Autowired
    VendasService vendasService;

    @Override
    public double getSaldo(Long idEvento, String emailParticipante) {
        Evento evento = this.findEventoById(idEvento);
        Participante participante = this.findParticipanteByEmail(emailParticipante);
        ContaCashless contaCashless = contaCashlessRepository.findByParticipanteAndEvento(participante, evento);

        if(contaCashless == null || contaCashless.getId() == null) return 0;
        return contaCashless.getValorAtual();
    }

    @Override
    public Iterable<MovimentoCashless> getExtrato(Long idEvento, String emailParticipante) {
        Evento evento = this.findEventoById(idEvento);
        Participante participante = this.findParticipanteByEmail(emailParticipante);
        ContaCashless contaCashless = this.findContaCashless(participante, evento);

        return movimentoCashlessRepository.findByContaCashless(contaCashless);
    }

    @Override
    public CarregamentoCashless rechargeConta(Long idEvento, CarregamentoModel carregamentoModel) {
        String participanteEmail = carregamentoModel.getParticipante();
        double valor = carregamentoModel.getValor();

        if(valor <= 0) throw new IllegalArgumentException("O valor deve ser maior que 0.");

        Evento evento = this.findEventoById(idEvento);
        Participante participante = this.findParticipanteByEmail(participanteEmail);
        ContaCashless contaCashless = this.findContaCashless(participante, evento);
        PagamentoCashless pagamentoCashless = this.createPagamentoCashless(contaCashless,
                participante, valor);

        return createCarregamentoCashless(contaCashless, pagamentoCashless, valor);
    }

    @Override
    public PagamentoCashless validatePagamento(ValidarPagamentoModel validarPagamentoModel) {
        int entidade = validarPagamentoModel.getEntidade();
        int referencia =validarPagamentoModel.getReferencia();
        double valor = validarPagamentoModel.getValor();

        PagamentoCashless pagamento = pagamentoRepository.findByReferenciaAndEntidadeCashless(referencia, entidade)
                .orElseThrow(() -> new IllegalArgumentException("Pagamento não encontrado para a referência e entidade fornecidas."));

        this.validatePagamento(pagamento, valor);

        ContaCashless contaCashless = pagamento.getContaCashless();
        contaCashless.setValorAtual(contaCashless.getValorAtual() + pagamento.getValor());
        contaCashlessRepository.save(contaCashless);

        pagamento.setData_validado(LocalDateTime.now());

        return pagamentoRepository.save(pagamento);
    }

    private Evento findEventoById(Long idEvento) {
        return eventoRepository.findById(idEvento)
                .orElseThrow(() -> new IllegalArgumentException("Evento com id " + idEvento + " não encontrado"));
    }

    private Participante findParticipanteByEmail(String email) {
        return participanteRepository.findByEmail(email)
                .orElseThrow(() -> new IllegalArgumentException("Participante não encontrado."));
    }

    private ContaCashless findContaCashless(Participante participante, Evento evento) {
        ContaCashless conta = contaCashlessRepository.findByParticipanteAndEvento(participante, evento);

        if (conta == null || conta.getId() == null) throw new IllegalArgumentException("Nenhuma conta encontrada para o evento fornecido.");
        return conta;
    }

    private PagamentoCashless createPagamentoCashless(ContaCashless contaCashless, Participante participante, double valor) {
        PagamentoCashless pagamento = new PagamentoCashless();
        pagamento.setContaCashless(contaCashless);
        pagamento.setEntidade(pagamentoRepository.count() <= 1 ? 12345 : vendasService.generateEntidade(participante));
        pagamento.setReferencia(pagamentoRepository.count() <= 1 ? 12345643 : vendasService.generateReferencia());
        pagamento.setValor(pagamentoRepository.count() <= 1 ? 40 : valor);
        pagamento.setData_compra(LocalDateTime.now());
        pagamento.setData_validado(null);

        return pagamentoRepository.save(pagamento);
    }

    private CarregamentoCashless createCarregamentoCashless(ContaCashless contaCashless, PagamentoCashless pagamento, double valor) {
        CarregamentoCashless carregamento = new CarregamentoCashless();
        carregamento.setTipoMovimento(TipoMovimento.RECARGA);
        carregamento.setContaCashless(contaCashless);
        carregamento.setValor(valor);
        carregamento.setSaldo(contaCashless.getValorAtual() + valor);
        carregamento.setData(LocalDateTime.now());
        carregamento.setPagamentoCashless(pagamento);

        return movimentoCashlessRepository.save(carregamento);
    }

    private void validatePagamento(PagamentoCashless pagamento, double valor) {
        if (pagamento.getData_validado() != null) {
            throw new IllegalArgumentException("Pagamento já validado.");
        }
        if (pagamento.getValor() != valor) {
            throw new IllegalArgumentException("O valor do pagamento não corresponde.");
        }
        if (new Random().nextDouble() > 0.8) {
            throw new IllegalArgumentException("O pagamento falhou.");
        }
    }

}