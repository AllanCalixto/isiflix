package br.com.professorisidro.naturassp.controller;

import br.com.professorisidro.naturassp.model.Produto;
import br.com.professorisidro.naturassp.service.IProdutoService;
import br.com.professorisidro.naturassp.service.IUploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
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
    public ResponseEntity<String> uploadFoto(@RequestParam(name = "arquivo")MultipartFile arquivo) {

        String path = upload.uploadFile(arquivo);
        if (path != null) {
            return ResponseEntity.status(201).body(path);
        }
        return ResponseEntity.badRequest().build();
    }
}
