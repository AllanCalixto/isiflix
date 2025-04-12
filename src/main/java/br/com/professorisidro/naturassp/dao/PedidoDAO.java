package br.com.professorisidro.naturassp.dao;

import br.com.professorisidro.naturassp.model.Cliente;
import br.com.professorisidro.naturassp.model.Pedido;
import org.springframework.data.repository.CrudRepository;

import java.util.ArrayList;

public interface PedidoDAO extends CrudRepository<Pedido, Integer> {

    public ArrayList<Pedido> findAllByCliente(Cliente cliente);
}
