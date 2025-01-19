package pt.upskill.upfest.services.cashless.compra;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pt.upskill.upfest.entities.*;
import pt.upskill.upfest.enums.TipoMovimento;
import pt.upskill.upfest.models.CompraModel;
import pt.upskill.upfest.repositories.cashless.ContaCashlessRepository;
import pt.upskill.upfest.repositories.cashless.MovimentoCashlessRepository;
import pt.upskill.upfest.repositories.cashless.ProdutoComercianteRepository;
import pt.upskill.upfest.repositories.evento.EventoRepository;
import pt.upskill.upfest.repositories.vendas.ParticipanteRepository;

import java.time.LocalDateTime;

@Service
public class CompraServiceImpl implements CompraService {
    @Autowired
    EventoRepository eventoRepository;
    @Autowired
    ParticipanteRepository participanteRepository;
    @Autowired
    ContaCashlessRepository contaCashlessRepository;
    @Autowired
    ProdutoComercianteRepository produtoComercianteRepository;
    @Autowired
    MovimentoCashlessRepository movimentoCashlessRepository;

    @Override
    public GastoCashless registarCompra(Long idEvento, CompraModel compraModel) {
        Evento evento = this.findEventoById(idEvento);
        ProdutoComerciante produtoComerciante = this.findProdutoById(compraModel.getProduto(), idEvento);
        Participante participante = this.findParticipanteByEmail(compraModel.getParticipante());
        ContaCashless contaCashless = this.findContaCashless(participante, evento);

        double total = this.calculateTotal(produtoComerciante.getValor(), compraModel.getQuantidade());
        validateSaldo(contaCashless, total);

        GastoCashless gastoCashless = createGastoCashless(contaCashless, produtoComerciante, compraModel, total);

        contaCashless.setValorAtual(contaCashless.getValorAtual() - total);
        contaCashlessRepository.save(contaCashless);

        return movimentoCashlessRepository.save(gastoCashless);
    }

    private Evento findEventoById(Long idEvento) {
        return eventoRepository.findById(idEvento)
                .orElseThrow(() -> new IllegalArgumentException("Evento com id " + idEvento + " não encontrado."));
    }

    private ProdutoComerciante findProdutoById(Long idProduto, Long idEvento) {
        ProdutoComerciante produto = produtoComercianteRepository.findById(idProduto)
                .orElseThrow(() -> new IllegalArgumentException("Produto não encontrado."));

        if (!produto.getComerciante().getEvento().getId().equals(idEvento)) {
            throw new IllegalArgumentException("O produto não pertence a este evento.");
        }

        return produto;
    }

    private Participante findParticipanteByEmail(String email) {
        return participanteRepository.findByEmail(email)
                .orElseThrow(() -> new IllegalArgumentException("Participante não encontrado."));
    }

    private ContaCashless findContaCashless(Participante participante, Evento evento) {
        ContaCashless contaCashless = contaCashlessRepository.findByParticipanteAndEvento(participante, evento);

        if (contaCashless == null || contaCashless.getId() == null) {
            throw new IllegalArgumentException("Nenhuma conta cashless encontrada para o participante neste evento.");
        }

        return contaCashless;
    }

    private double calculateTotal(double unitPrice, int quantity) {
        return unitPrice * quantity;
    }

    private void validateSaldo(ContaCashless contaCashless, double total) {
        if (contaCashless.getValorAtual() < total) {
            throw new IllegalArgumentException("Os fundos não cobrem o custo total.");
        }
    }

    private GastoCashless createGastoCashless(ContaCashless contaCashless, ProdutoComerciante produto, CompraModel model, double total) {
        GastoCashless gastoCashless = new GastoCashless();
        gastoCashless.setTipoMovimento(TipoMovimento.GASTO);
        gastoCashless.setContaCashless(contaCashless);
        gastoCashless.setValor(total);
        gastoCashless.setSaldo(contaCashless.getValorAtual() - total);
        gastoCashless.setData(LocalDateTime.now());
        gastoCashless.setProdutoComerciante(produto);
        gastoCashless.setQuantidade(model.getQuantidade());
        gastoCashless.setValor_unitario(produto.getValor());

        return gastoCashless;
    }
}
