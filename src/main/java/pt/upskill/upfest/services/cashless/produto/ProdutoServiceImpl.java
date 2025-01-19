package pt.upskill.upfest.services.cashless.produto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pt.upskill.upfest.entities.Comerciante;
import pt.upskill.upfest.entities.ProdutoComerciante;
import pt.upskill.upfest.models.ProdutoComercianteModel;
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
        Comerciante comerciante = comercianteRepository.findById(idComerciante).orElseThrow(() ->
                new IllegalArgumentException("Comerciante com id " + idComerciante + " não encontrado."));

        if (!comerciante.getEvento().getId().equals(idEvento)) {
            throw new IllegalArgumentException("O Comerciante não faz parte deste evento.");
        }

        return produtoComercianteRepository.findByComerciante(comerciante);
    }

    @Override
    public ProdutoComerciante criarProduto(Long idEvento, Long idComerciante,
                                           ProdutoComercianteModel produtoComercianteModel) {
        Comerciante comerciante = comercianteRepository.findById(idComerciante).orElseThrow(() ->
                new IllegalArgumentException("Comerciante com id " + idComerciante + " não encontrado."));

        ProdutoComerciante produtoComerciante = new ProdutoComerciante();
        produtoComerciante.setDesignacao(produtoComercianteModel.getDesignacao());
        produtoComerciante.setValor(produtoComercianteModel.getValor());
        produtoComerciante.setComerciante(comerciante);

        return produtoComercianteRepository.save(produtoComerciante);
    }

    @Override
    public ProdutoComerciante editarProduto(Long idEvento, Long idComerciante, Long idProduto,
                                            ProdutoComercianteModel produtoComercianteModel) {
        Comerciante comerciante = comercianteRepository.findById(idComerciante).orElseThrow(() ->
                new IllegalArgumentException("Comerciante com id " + idComerciante + " não encontrado."));
        ProdutoComerciante produtoComerciante = produtoComercianteRepository.findById(idProduto).orElseThrow(() ->
                new IllegalArgumentException("Produto com id " + idProduto + " não encontrado."));

        if(!produtoComerciante.getComerciante().getId().equals(comerciante.getId())){
            throw new IllegalArgumentException("O produto não faz parte deste comerciante.");
        }

        if(!comerciante.getEvento().getId().equals(idEvento)){
            throw new IllegalArgumentException("O Comerciante não faz parte deste evento.");
        }

        produtoComerciante.setDesignacao(produtoComercianteModel.getDesignacao());
        produtoComerciante.setValor(produtoComercianteModel.getValor());

        return produtoComercianteRepository.save(produtoComerciante);
    }

}
