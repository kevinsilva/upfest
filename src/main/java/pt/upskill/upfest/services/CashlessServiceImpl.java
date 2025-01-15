package pt.upskill.upfest.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pt.upskill.upfest.entities.ContaCashless;
import pt.upskill.upfest.entities.MovimentoCashless;
import pt.upskill.upfest.entities.Pagamento;
import pt.upskill.upfest.entities.PagamentoCashless;
import pt.upskill.upfest.repositories.ContaCashlessRepository;
import pt.upskill.upfest.repositories.MovimentoCashlessRepository;
import pt.upskill.upfest.repositories.PagamentoCashlessRepository;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class CashlessServiceImpl implements CashlessService {

    @Autowired
    private ContaCashlessRepository contaCashlessRepository;

    @Autowired
    private MovimentoCashlessRepository movimentoCashlessRepository;

    @Autowired
    private PagamentoCashlessRepository pagamentoCashlessRepository;

    @Override
    public double consultarSaldo(Long eventoId, String participanteEmail) {
        ContaCashless conta = contaCashlessRepository.findByEventoIdAndParticipanteEmail(eventoId, participanteEmail)
                .orElseThrow(() -> new RuntimeException("Conta não encontrada"));
        return conta.getValorAtual();
    }

    @Override
    public List<MovimentoCashless> consultarExtrato(Long eventoId, String participanteEmail) {
        return movimentoCashlessRepository.findByContaCashlessEventoIdAndContaCashlessParticipanteEmail(eventoId, participanteEmail);
    }

    @Override
    public byte[] gerarExtratoExcel(Long eventoId, String participanteEmail) {
        // Geração de arquivo Excel com Apache POI ou similar.
        throw new UnsupportedOperationException("Geração de Excel não implementada.");
    }

    @Override
    public ContaCashless carregarSaldo(Long eventoId, String participanteEmail, double valor) {
        ContaCashless conta = contaCashlessRepository.findByEventoIdAndParticipanteEmail(eventoId, participanteEmail)
                .orElseThrow(() -> new RuntimeException("Conta não encontrada"));

        double novoSaldo = conta.getValorAtual() + valor;
        conta.setValorAtual(novoSaldo);

        MovimentoCashless movimento = new MovimentoCashless();
        movimento.setContaCashless(conta);
        movimento.setValor(valor);
        movimento.setSaldo(novoSaldo);
        movimento.setData(LocalDateTime.now());
        movimentoCashlessRepository.save(movimento);

        return contaCashlessRepository.save(conta);
    }

    @Override
    public PagamentoCashless validarPagamento(Pagamento pagamento) {
        // Simulação de validação de pagamento.
        PagamentoCashless pagamentoCashless = new PagamentoCashless();
        pagamentoCashless.setPagamento(pagamento);
        pagamentoCashlessRepository.save(pagamentoCashless);

        return pagamentoCashless;
    }
}
