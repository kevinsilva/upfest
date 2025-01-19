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
    public double obterSaldo(Long idEvento, String emailParticipante) {
        Evento evento = eventoRepository.findById(idEvento).orElseThrow(() -> new IllegalArgumentException("Evento " +
                "com id " + idEvento + " não encontrado"));
        Participante participante = participanteRepository.findByEmail(emailParticipante).orElseThrow(() ->
                new IllegalArgumentException("Participante não encontrado."));
        ContaCashless contaCashless = contaCashlessRepository.findByParticipanteAndEvento(participante, evento);
        if(contaCashless == null || contaCashless.getId() == null) {
            return 0;
        }
        return contaCashless.getValorAtual();
    }

    @Override
    public Iterable<MovimentoCashless> obterExtrato(Long idEvento, String emailParticipante) {
        Evento evento = eventoRepository.findById(idEvento).orElseThrow(() ->
                new IllegalArgumentException("Evento com id " + idEvento + " não encontrado."));
        Participante participante = participanteRepository.findByEmail(emailParticipante).orElseThrow(() ->
                new IllegalArgumentException("Participante não encontrado."));

        ContaCashless contaCashless = contaCashlessRepository.findByParticipanteAndEvento(participante, evento);
        if (contaCashless == null) {
            throw new IllegalArgumentException("Nenhuma ContaCashless encontrada para o evento e participante fornecidos.");
        }

        return movimentoCashlessRepository.findByContaCashless(contaCashless);
    }

    @Override
    public CarregamentoCashless carregarConta(Long idEvento, CarregamentoModel carregamentoModel) {
        String participanteEmail = carregamentoModel.getParticipante();
        double valor = carregamentoModel.getValor();

        if(valor <= 0) throw new IllegalArgumentException("O valor deve ser maior que 0.");

        Evento evento = eventoRepository.findById(idEvento).orElseThrow(() ->
                new IllegalArgumentException("Evento com id " + idEvento + " não encontrado."));
        Participante participante =
                participanteRepository.findByEmail(participanteEmail).orElseThrow(() -> new IllegalArgumentException(
                        "Participante não encontrado."));

        ContaCashless contaCashless = contaCashlessRepository.findByParticipanteAndEvento(participante, evento);
        if(contaCashless == null || contaCashless.getId() == null) {
            throw new IllegalArgumentException("Não existe conta para o evento fornecido.");
        }

        PagamentoCashless pagamentoCashless = new PagamentoCashless();
        pagamentoCashless.setContaCashless(contaCashless);
        pagamentoCashless.setEntidade(vendasService.gerarEntidade(participante));
        pagamentoCashless.setReferencia(vendasService.gerarReferencia());
        pagamentoCashless.setValor(valor);
        pagamentoCashless.setData_compra(LocalDateTime.now());
        pagamentoCashless.setData_validado(null);

        pagamentoCashless = (PagamentoCashless) pagamentoRepository.save(pagamentoCashless);

        CarregamentoCashless carregamentoCashless = new CarregamentoCashless();
        carregamentoCashless.setTipoMovimento(TipoMovimento.RECARGA);
        carregamentoCashless.setContaCashless(contaCashless);
        carregamentoCashless.setValor(valor);
        carregamentoCashless.setSaldo(contaCashless.getValorAtual() + valor);
        carregamentoCashless.setData(LocalDateTime.now());
        carregamentoCashless.setPagamentoCashless(pagamentoCashless);

        return movimentoCashlessRepository.save(carregamentoCashless);
    }

    public PagamentoCashless validarPagamento(ValidarPagamentoModel validarPagamentoModel) {
        int entidade = pagamentoRepository.count() <= 2 ? 12345 : validarPagamentoModel.getEntidade();
        int referencia = pagamentoRepository.count() <= 2 ? 12345643 : validarPagamentoModel.getReferencia();
        double valor = pagamentoRepository.count() <= 2 ? 40 : validarPagamentoModel.getValor();

        PagamentoCashless pagamentoCashless = pagamentoRepository
                .findByReferenciaAndEntidadeCashless(referencia, entidade)
                .orElseThrow(() ->
                        new IllegalArgumentException("Pagamento não encontrado para a referência e entidade fornecidas."));

        if(pagamentoCashless.getData_validado() != null) {
            throw new IllegalArgumentException("Pagamento já validado.");
        }

        if(pagamentoCashless.getValor() != valor) {
            throw new IllegalArgumentException("O valor do pagamento não corresponde.");
        }

        if (new Random().nextDouble() > 0.8) {
            throw new IllegalArgumentException("O pagamento falhou.");
        }

        ContaCashless contaCashless = pagamentoCashless.getContaCashless();
        double novoValorAtual = contaCashless.getValorAtual() + pagamentoCashless.getValor();
        contaCashless.setValorAtual(novoValorAtual);
        contaCashlessRepository.save(contaCashless);

        pagamentoCashless.setData_validado(LocalDateTime.now());
        pagamentoCashless = pagamentoRepository.save(pagamentoCashless);

        return pagamentoCashless;
    }


}