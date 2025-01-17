package pt.upskill.upfest.services.cashless.produto;

import pt.upskill.upfest.entities.ProdutoComerciante;
import pt.upskill.upfest.models.ProdutoComercianteModel;

import java.util.List;

public interface ProdutoService {
    List<ProdutoComerciante> listarProdutos(Long idEvento, Long idComerciante);
    ProdutoComerciante criarProduto(Long idEvento, Long idComerciante, ProdutoComercianteModel produtoComercianteModel);
    ProdutoComerciante editarProduto(Long idEvento, Long idComerciante, Long idProduto, ProdutoComercianteModel produtoComercianteModel);
}
