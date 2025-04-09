package br.com.professorisidro.naturassp.service;

import br.com.professorisidro.naturassp.model.Categoria;
import br.com.professorisidro.naturassp.model.Produto;

import java.util.ArrayList;

public interface IProdutoService {

    public Produto inserirNovoProduto(Produto produto);

    public Produto alterarProduto(Produto produto);

    public ArrayList<Produto> listarTodos();

    public ArrayList<Produto> listarDisponiveis();

    public ArrayList<Produto> listarIndisponiveis();

    public ArrayList<Produto> listarPorCategoria(Categoria categoria);

    public Produto recuperarProdutoPorId(int id);

}
