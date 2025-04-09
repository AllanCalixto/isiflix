package br.com.professorisidro.naturassp.service;

import br.com.professorisidro.naturassp.dao.PedidoDAO;
import br.com.professorisidro.naturassp.model.ItemPedido;
import br.com.professorisidro.naturassp.model.Pedido;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PedidoServiceImpl implements IPedidoService {

    @Autowired
    private PedidoDAO dao;

    @Override
    public Pedido inserirPedido(Pedido novo) {
        try {
            double total = 0.0;
//            Aqui em tese vem a regra de negocios
            for (ItemPedido item: novo.getItensPedido()) {
                item.setPrecoUnitario(item.getProduto().getPreco());
                if (item.getQtdeItem() >= 5) { // Aplicar 20% de desconto.
                    item.setPrecoTotal(item.getPrecoUnitario() * item.getQtdeItem() * 0.8);
                } else {
                    item.setPrecoTotal(item.getPrecoUnitario() * item.getQtdeItem());
                }
                total += item.getPrecoTotal();
            }
            for (ItemPedido item: novo.getItensPedido()) {
                item.setPedido(novo);
            }
            novo.setValorTotal(total);
            dao.save(novo);
            return novo;
        }catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }
}
