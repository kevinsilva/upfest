package pt.upskill.upfest.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pt.upskill.upfest.entities.ContaCashless;
import pt.upskill.upfest.entities.Pagamento;
import pt.upskill.upfest.entities.PagamentoCashless;
import pt.upskill.upfest.repositories.ContaCashlessRepository;
import pt.upskill.upfest.repositories.PagamentoCashlessRepository;

@Service
public class PagamentoCashlessServiceImpl implements PagamentoCashlessService {

    @Autowired
    private PagamentoCashlessRepository pagamentoCashlessRepository;

    @Autowired
    private ContaCashlessRepository contaCashlessRepository;

    @Override
    public PagamentoCashless registrarPagamento(Long contaId, Pagamento pagamento) {
        ContaCashless conta = contaCashlessRepository.findById(contaId)
                .orElseThrow(() -> new RuntimeException("Conta não encontrada"));

        PagamentoCashless pagamentoCashless = new PagamentoCashless();
        pagamentoCashless.setContaCashless(conta);
        pagamentoCashless.setPagamento(pagamento);
        return pagamentoCashlessRepository.save(pagamentoCashless);
    }
}
