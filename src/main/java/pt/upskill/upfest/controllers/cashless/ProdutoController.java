package pt.upskill.upfest.controllers.cashless;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pt.upskill.upfest.entities.ProdutoComerciante;
import pt.upskill.upfest.models.ProdutoComercianteModel;
import pt.upskill.upfest.services.cashless.produto.ProdutoService;

import java.util.List;

@RestController
@RequestMapping("/cashless")
public class ProdutoController {
    @Autowired
    ProdutoService produtoService;

    @GetMapping("{id_evento}/comerciantes/{id_comerciante}/produtos/listar")
    public List<ProdutoComerciante> listarProdutos(@PathVariable("id_evento") Long idEvento, @PathVariable("id_comerciante") Long idComerciante) {
        return produtoService.listarProdutos(idEvento, idComerciante);
    }

    @PostMapping("{id_evento}/comerciantes/{id_comerciante}/produtos/criar")
    public ProdutoComerciante criarProduto(@PathVariable("id_evento") Long idEvento, @PathVariable("id_comerciante") Long idComerciante, @RequestBody ProdutoComercianteModel produtoComercianteModel) {
        return produtoService.criarProduto(idEvento, idComerciante, produtoComercianteModel);
    }

    @PostMapping("{id_evento}/comerciantes/{id_comerciante}/produtos/{id_produto}/editar")
    public ProdutoComerciante editarProduto(@PathVariable("id_evento") Long idEvento, @PathVariable("id_comerciante") Long idComerciante, @PathVariable("id_produto") Long idProduto, @RequestBody ProdutoComercianteModel produtoComercianteModel) {
        return produtoService.editarProduto(idEvento, idComerciante, idProduto, produtoComercianteModel);
    }

}
