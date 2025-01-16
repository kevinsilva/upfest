package pt.upskill.upfest.services.cashless.produto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pt.upskill.upfest.entities.Comerciante;
import pt.upskill.upfest.entities.ProdutoComerciante;
import pt.upskill.upfest.models.NovoProdutoComerciante;
import pt.upskill.upfest.repositories.cashless.ComercianteRepository;
import pt.upskill.upfest.repositories.cashless.ProdutoComercianteRepository;

import java.util.List;

@Service
public class ProdutoServiceImpl implements ProdutoService {
    @Autowired
    ProdutoComercianteRepository produtoComercianteRepository;
    @Autowired
    ComercianteRepository comercianteRepository;

    @Override
    public List<ProdutoComerciante> listarProdutos(Long idEvento, Long idComerciante) {
        Comerciante comerciante = comercianteRepository.findById(idComerciante).orElseThrow(() -> new IllegalArgumentException("Comerciante with id " + idComerciante + " not found"));

        if (!comerciante.getEvento().getId_evento().equals(idEvento)) {
            throw new IllegalArgumentException("Comerciante is not part of this event");
        }

        return produtoComercianteRepository.findByComerciante(comerciante);
    }

    @Override
    public ProdutoComerciante criarProduto(Long idEvento, Long idComerciante, NovoProdutoComerciante novoProdutoComerciante) {
        Comerciante comerciante = comercianteRepository.findById(idComerciante).orElseThrow(() -> new IllegalArgumentException("Comerciante with id " + idComerciante + " not found"));

        ProdutoComerciante produtoComerciante = new ProdutoComerciante();
        produtoComerciante.setDesignacao(novoProdutoComerciante.getDesignacao());
        produtoComerciante.setValor(novoProdutoComerciante.getValor());
        produtoComerciante.setComerciante(comerciante);

        return produtoComercianteRepository.save(produtoComerciante);
    }

    @Override
    public ProdutoComerciante editarProduto(Long idEvento, Long idComerciante, Long idProduto, NovoProdutoComerciante novoProdutoComerciante) {
        Comerciante comerciante = comercianteRepository.findById(idComerciante).orElseThrow(() -> new IllegalArgumentException("Comerciante with id " + idComerciante + " not found"));
        ProdutoComerciante produtoComerciante = produtoComercianteRepository.findById(idProduto).orElseThrow(() -> new IllegalArgumentException("Produto with id " + idProduto + " not found"));

        if(!produtoComerciante.getComerciante().getId().equals(comerciante.getId())){
            throw new IllegalArgumentException("Product is not part of this comerciante");
        }

        if(!comerciante.getEvento().getId_evento().equals(idEvento)){
            throw new IllegalArgumentException("Comerciante is not part of this event");
        }

        produtoComerciante.setDesignacao(novoProdutoComerciante.getDesignacao());
        produtoComerciante.setValor(novoProdutoComerciante.getValor());

        return produtoComercianteRepository.save(produtoComerciante);
    }

}
