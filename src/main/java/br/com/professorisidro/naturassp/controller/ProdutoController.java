package br.com.professorisidro.naturassp.controller;

import br.com.professorisidro.naturassp.model.Categoria;
import br.com.professorisidro.naturassp.model.Produto;
import br.com.professorisidro.naturassp.service.IProdutoService;
import br.com.professorisidro.naturassp.service.IUploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;

@RestController
@CrossOrigin("*")
public class ProdutoController {

    @Autowired
    private IProdutoService service;

    @Autowired
    private IUploadService upload;

    @PostMapping("/produto")
    public ResponseEntity<Produto> novoProduto(@RequestBody Produto novo) {

        try {
            service.inserirNovoProduto(novo);
            return ResponseEntity.status(201).body(novo);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return ResponseEntity.badRequest().build();
    }

    @PostMapping("/produto/upload")
    public ResponseEntity<String> uploadFoto(@RequestParam(name = "arquivo") MultipartFile arquivo) {

        String path = upload.uploadFile(arquivo);
        if (path != null) {
            return ResponseEntity.status(201).body(path);
        }
        return ResponseEntity.badRequest().build();
    }

    @GetMapping("/produto")
    public ResponseEntity<ArrayList<Produto>> recuperarTodos() {
        return ResponseEntity.ok(service.listarDisponiveis());
    }

    @GetMapping("/produto/categoria/{id}")
    public ResponseEntity<ArrayList<Produto>> recuperarPorCategoria(@PathVariable(name = "id") int idCat) {
        Categoria cat = new Categoria();
        cat.setId(idCat);
        return ResponseEntity.ok(service.listarPorCategoria(cat));
    }

    @GetMapping("/produto/{id}")
    public ResponseEntity<Produto> recuperarPorId(@PathVariable(name = "id") int idProduto) {
        Produto prod = service.recuperarProdutoPorId(idProduto);
        if (prod != null) {
            return ResponseEntity.ok(prod);
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/produto/busca")
    public ResponseEntity<ArrayList<Produto>> buscarPorPalavraChave(@RequestParam (name = "key") String key) {
        System.out.println("key = " +key);
        if(key != null) {
            return ResponseEntity.ok(service.listarPorPalavraChave(key));
        } else {
            return ResponseEntity.badRequest().build();
        }

    }
}
