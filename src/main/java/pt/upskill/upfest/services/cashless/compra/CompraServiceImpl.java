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
        Evento evento = eventoRepository
                .findById(idEvento).orElseThrow(() ->
                        new IllegalArgumentException("Evento com id " + idEvento + " não encontrado."));


        ProdutoComerciante produtoComerciante =
                produtoComercianteRepository.findById(compraModel.getProduto()).orElseThrow(()->
                        new IllegalArgumentException("Produto não encontrado."));

        if(!produtoComerciante.getComerciante().getEvento().getId().equals(idEvento)) throw new
                IllegalArgumentException("O produto não pertence a este evento.");

        Participante participante = participanteRepository.findByEmail(compraModel.getParticipante()).orElseThrow(() ->
                new IllegalArgumentException("Participante não encontrado."));
        ContaCashless contaCashless =
                contaCashlessRepository.findByParticipanteAndEvento(participante, evento);
        if (contaCashless == null || contaCashless.getId() == null)  throw new
                IllegalArgumentException("Nenhuma conta cashless " +
                " encontrada para o participante fornecido neste evento..");

        double total = produtoComerciante.getValor() * compraModel.getQuantidade();
        if(contaCashless.getValorAtual() < total) throw new IllegalArgumentException("Os fundos não cobrem " +
                "o custo total.");

        GastoCashless gastoCashless = new GastoCashless();
        gastoCashless.setTipoMovimento(TipoMovimento.GASTO);
        gastoCashless.setContaCashless(contaCashless);
        gastoCashless.setValor(total);
        gastoCashless.setSaldo(contaCashless.getValorAtual() - total);
        gastoCashless.setData(LocalDateTime.now());
        gastoCashless.setProdutoComerciante(produtoComerciante);
        gastoCashless.setQuantidade(compraModel.getQuantidade());
        gastoCashless.setValor_unitario(produtoComerciante.getValor());
        gastoCashless = movimentoCashlessRepository.save(gastoCashless);

        contaCashless.setValorAtual(contaCashless.getValorAtual() - total);
        contaCashlessRepository.save(contaCashless);

        return gastoCashless;
    }
}
