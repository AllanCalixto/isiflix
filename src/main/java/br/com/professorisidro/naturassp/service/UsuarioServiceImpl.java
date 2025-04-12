package br.com.professorisidro.naturassp.service;

import br.com.professorisidro.naturassp.dao.UsuarioDAO;
import br.com.professorisidro.naturassp.model.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UsuarioServiceImpl implements IUsuarioService{

    @Autowired
    private UsuarioDAO dao;

    @Override
    public Usuario recuperarUsuario(Usuario original) {
        return dao.findByUsernameOrEmail(original.getUsername(), original.getEmail());
    }
}
