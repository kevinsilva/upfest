package pt.upskill.upfest.services.cashless.produto;

import pt.upskill.upfest.entities.ProdutoComerciante;
import pt.upskill.upfest.models.NovoProdutoComerciante;

import java.util.List;

public interface ProdutoService {
    List<ProdutoComerciante> listarProdutos(Long idEvento, Long idComerciante);
    ProdutoComerciante criarProduto(Long idEvento, Long idComerciante, NovoProdutoComerciante novoProdutoComerciante);
    ProdutoComerciante editarProduto(Long idEvento, Long idComerciante, Long idProduto, NovoProdutoComerciante novoProdutoComerciante);
}
