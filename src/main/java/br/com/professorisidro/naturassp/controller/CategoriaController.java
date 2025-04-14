package br.com.professorisidro.naturassp.controller;

import br.com.professorisidro.naturassp.model.Categoria;
import br.com.professorisidro.naturassp.service.ICategoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@CrossOrigin("*")
public class CategoriaController {

    @Autowired
    private ICategoriaService service;

    @GetMapping("/categoria")
    public ResponseEntity<ArrayList<Categoria>> listarTodas() {
        return ResponseEntity.ok().body(service.recuperarTodasCategorias());
    }

    @GetMapping("/categoria/search")
    public ResponseEntity<ArrayList<Categoria>> recuperarPorPalavraChave(@RequestParam(name = "key") String palavraChave) {
        if (palavraChave != null) {
            return ResponseEntity.ok().body(service.recuperarPorPalavraChave(palavraChave));
        }
        return ResponseEntity.badRequest().build();
    }

    @PostMapping("/categoria")
    public ResponseEntity<Categoria> adicionarNova(@RequestBody Categoria categoria) {
        if (categoria.getId() != null) {
            categoria.setId(null);
        }
        Categoria resultado = service.inserirNovaCategoria(categoria);
        if (resultado != null) {
            return ResponseEntity.status(201).body(resultado);
        }
        return ResponseEntity.badRequest().build();
    }

    @PutMapping("/categoria")
    public ResponseEntity<Categoria> alterarDados(@RequestBody Categoria categoria) {
        Categoria resultado = service.alterarCategoria(categoria);
        if (resultado != null) {
            return ResponseEntity.ok(resultado);
        }
        return ResponseEntity.badRequest().build();
    }
}
