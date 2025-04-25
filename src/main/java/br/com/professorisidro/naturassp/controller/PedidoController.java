package br.com.professorisidro.naturassp.controller;

import br.com.professorisidro.naturassp.model.Cliente;
import br.com.professorisidro.naturassp.model.Pedido;
import br.com.professorisidro.naturassp.service.IClienteService;
import br.com.professorisidro.naturassp.service.IPedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.xml.crypto.Data;
import java.time.ZoneId;
import java.util.Date;

@RestController
@CrossOrigin("*")
public class PedidoController {

    @Autowired
    private IPedidoService service;

    @Autowired
    private IClienteService clienteService;

    @PostMapping("/pedido")
    public ResponseEntity<Pedido> inserirNovoPedido(@RequestBody Pedido novo) {

        novo.setDataPedido(new Date().toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
    	System.out.println("--- PEDIDO ");
    	System.out.println(novo.getObservacoes());
    	System.out.println(novo.getDataPedido());
    	System.out.println(novo.getItensPedido());
    	System.out.println(novo.getCliente().getIdCliente());
    	
    	System.out.println("-------------------------------");

        // Antes de gravar o pedido, realizar a inclusão do cliente na base
        Cliente cli = clienteService.atualizarDados(novo.getCliente());
    	novo.setCliente(cli);
        
    	novo = service.inserirPedido(novo);
        novo = service.inserirPedido(novo);
        if (novo != null) {
            return ResponseEntity.status(201).body(novo);
        } else {
        	return ResponseEntity.status(400).build();        	
        }
    }
}
