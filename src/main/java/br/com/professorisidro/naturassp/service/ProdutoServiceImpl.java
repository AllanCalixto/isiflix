package br.com.professorisidro.naturassp.service;

import br.com.professorisidro.naturassp.dao.ProdutoDAO;
import br.com.professorisidro.naturassp.model.Categoria;
import br.com.professorisidro.naturassp.model.Produto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class ProdutoServiceImpl implements IProdutoService {

    @Autowired
    private ProdutoDAO dao;

    @Override

    public Produto inserirNovoProduto(Produto produto) {

        try {
            dao.save(produto);
            return produto;
        } catch (Exception ex) {
            System.out.println("----- ProdutoService.inserirNovoProduto -----");
            ex.printStackTrace();
            System.out.println("--------------------");
        }
        return null;
    }

    @Override
    public Produto alterarProduto(Produto produto) {

        try {
            dao.save(produto);
            return produto;
        } catch (Exception ex) {
            System.out.println("----- ProdutoService.alterarProduto -----");
            ex.printStackTrace();
            System.out.println("--------------------");
        }
        return null;
    }

    @Override
    public ArrayList<Produto> listarTodos() {
        return (ArrayList<Produto>) dao.findAll();
    }

    @Override
    public ArrayList<Produto> listarDisponiveis() {
        return dao.findAllByDisponivel(1); // considero todos os produtos 1 como diponíveis.
    }

    @Override
    public ArrayList<Produto> listarIndisponiveis() {
        return dao.findAllByDisponivel(0);
    }

    @Override
    public ArrayList<Produto> listarPorCategoria(Categoria categoria) {
        return dao.findAllByDisponivelAndCategoria(1, categoria);
    }

    @Override
    public ArrayList<Produto> listarPorPalavraChave(String palavraChave) {
        return dao.findAllByNomeContainingOrDetalheContaining(palavraChave, palavraChave);
    }

    @Override
    public Produto recuperarProdutoPorId(int id) {
        return dao.findById(id).orElse(null);
    }

}
