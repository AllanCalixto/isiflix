package br.com.professorisidro.naturassp.dao;

import br.com.professorisidro.naturassp.model.Categoria;
import org.springframework.data.repository.CrudRepository;

import java.util.ArrayList;

public interface CategoriaDAO extends CrudRepository<Categoria, Integer> {

    // Consultas customizadas.

    // 1- Categoria por palavra chave.
    public ArrayList<Categoria> findByNomeContaining(String palavra);
}
