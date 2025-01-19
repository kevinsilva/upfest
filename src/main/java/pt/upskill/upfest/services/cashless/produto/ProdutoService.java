package pt.upskill.upfest.services.cashless.produto;

import pt.upskill.upfest.entities.ProdutoComerciante;
import pt.upskill.upfest.models.ProdutoComercianteModel;

import java.util.List;

public interface ProdutoService {
    List<ProdutoComerciante> listProdutos(Long idEvento, Long idComerciante);
    ProdutoComerciante createProduto(Long idEvento, Long idComerciante, ProdutoComercianteModel produtoComercianteModel);
    ProdutoComerciante editProduto(Long idEvento, Long idComerciante, Long idProduto, ProdutoComercianteModel produtoComercianteModel);
}
