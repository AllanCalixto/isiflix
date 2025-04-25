package br.com.professorisidro.naturassp.service;

import br.com.professorisidro.naturassp.dao.ClienteDAO;
import br.com.professorisidro.naturassp.model.Cliente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ClienteServiceImpl implements IClienteService{

    @Autowired
    private ClienteDAO dao;

    @Override
    public Cliente buscarPeloTelefone(String telefone) {
        if (telefone.charAt(0) == '0') {
            telefone= telefone.substring(1);
        }
        return dao.findByTelefone(telefone);
    }

    @Override
    public Cliente atualizarDados(Cliente dadosOriginais) {
        return dao.save(dadosOriginais);
    }
}
