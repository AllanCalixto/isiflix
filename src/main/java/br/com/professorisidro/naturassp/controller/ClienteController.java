package br.com.professorisidro.naturassp.controller;

import br.com.professorisidro.naturassp.model.Cliente;
import br.com.professorisidro.naturassp.service.IClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin("*")
public class ClienteController {

    @Autowired
    private IClienteService service;

    @GetMapping("/cliente/{cpf}")
    public ResponseEntity<Cliente> buscarPeloCPF(@PathVariable String cpf) {
        Cliente resultado = service.buscarPeloCPF(cpf);
        if (resultado != null) {
            return ResponseEntity.ok(resultado);
        }
        return ResponseEntity.notFound().build();
    }
}
