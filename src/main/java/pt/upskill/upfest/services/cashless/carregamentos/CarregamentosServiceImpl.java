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

//    @Override
//    public double obterSaldo(Long idEvento, String emailParticipante) {
//        Evento evento = eventoRepository.findById(idEvento).orElseThrow(() -> new IllegalArgumentException("Evento " +
//                "com id " + idEvento + " não encontrado"));
//        Participante participante = participanteRepository.findByEmail(emailParticipante).orElseThrow(() ->
//                new IllegalArgumentException("Participante não encontrado."));
//        ContaCashless contaCashless = contaCashlessRepository.findByParticipanteAndEvento(participante, evento);
//        if(contaCashless == null || contaCashless.getId() == null) {
//            return 0;
//        }
//        return contaCashless.getValorAtual();
//    }

    @Override
    public double obterSaldo(Long idEvento, String emailParticipante) {
        Evento evento = findEventoById(idEvento);
        Participante participante = findParticipanteByEmail(emailParticipante);
        ContaCashless contaCashless = contaCashlessRepository.findByParticipanteAndEvento(participante, evento);

        if(contaCashless == null || contaCashless.getId() == null) return 0;
        return contaCashless.getValorAtual();
    }

    @Override
    public Iterable<MovimentoCashless> obterExtrato(Long idEvento, String emailParticipante) {
        Evento evento = findEventoById(idEvento);
        Participante participante = findParticipanteByEmail(emailParticipante);
        ContaCashless contaCashless = findContaCashless(participante, evento);
        return movimentoCashlessRepository.findByContaCashless(contaCashless);
    }

//    @Override
//    public Iterable<MovimentoCashless> obterExtrato(Long idEvento, String emailParticipante) {
//        Evento evento = eventoRepository.findById(idEvento).orElseThrow(() ->
//                new IllegalArgumentException("Evento com id " + idEvento + " não encontrado."));
//        Participante participante = participanteRepository.findByEmail(emailParticipante).orElseThrow(() ->
//                new IllegalArgumentException("Participante não encontrado."));
//
//        ContaCashless contaCashless = contaCashlessRepository.findByParticipanteAndEvento(participante, evento);
//        if (contaCashless == null) {
//            throw new IllegalArgumentException("Nenhuma ContaCashless encontrada para o evento e participante fornecidos.");
//        }
//
//        return movimentoCashlessRepository.findByContaCashless(contaCashless);
//    }

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

//    @Override
//    public CarregamentoCashless carregarConta(Long idEvento, CarregamentoModel carregamentoModel) {
//        if (carregamentoModel.getValor() <= 0) {
//            throw new IllegalArgumentException("O valor deve ser maior que 0.");
//        }
//
//        Evento evento = findEventoById(idEvento);
//        Participante participante = findParticipanteByEmail(carregamentoModel.getParticipante());
//        ContaCashless contaCashless = findContaCashless(participante, evento);
//
//        PagamentoCashless pagamentoCashless = createPagamentoCashless(contaCashless, carregamentoModel.getValor());
//        return createCarregamentoCashless(contaCashless, pagamentoCashless, carregamentoModel.getValor());
//    }

    public PagamentoCashless validarPagamento(ValidarPagamentoModel validarPagamentoModel) {
        System.out.println(pagamentoRepository.count());
        int entidade = pagamentoRepository.count() <= 3 ? 12345 : validarPagamentoModel.getEntidade();
        int referencia = pagamentoRepository.count() <= 3 ? 12345643 : validarPagamentoModel.getReferencia();
        double valor = pagamentoRepository.count() <= 3 ? 40 : validarPagamentoModel.getValor();

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

}