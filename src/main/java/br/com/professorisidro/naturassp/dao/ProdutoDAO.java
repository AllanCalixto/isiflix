package br.com.professorisidro.naturassp.dao;

import br.com.professorisidro.naturassp.model.Categoria;
import br.com.professorisidro.naturassp.model.Produto;
import org.springframework.data.repository.CrudRepository;

import java.util.ArrayList;

public interface ProdutoDAO extends CrudRepository<Produto, Integer> {

    public ArrayList<Produto> findAllByDisponivel(int disponivel);

    public ArrayList<Produto> findAllByDisponivelAndCategoria(int disponivel, Categoria cat);

    public ArrayList<Produto> findAllByCategoria(Categoria categoria);

    public ArrayList<Produto> findAllByNomeContainingOrDetalheContaining(String palavraChave, String palavraChave1);
}
