package pt.upskill.upfest.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pt.upskill.upfest.entities.Pagamento;
import pt.upskill.upfest.models.PagamentoModel;
import pt.upskill.upfest.repositories.PagamentoRepository;

import java.util.List;

@Service
public class VendasServiceImpl implements VendasService {

    @Autowired
    PagamentoRepository pagamentoRepository;

    //2.pagamentos
    @Override
    public Pagamento validarPagamento(PagamentoModel info) {
        Pagamento pagamento = new Pagamento();
        pagamento.setEntidade(info.getEntidade());
        pagamento.setReferencia(info.getReferencia());
        pagamento.setValor(info.getValor());
        return pagamentoRepository.save(pagamento);
    }

    @Override
    public List<Pagamento> getAllPagamentos() {
        return pagamentoRepository.findAll();
    }
}
