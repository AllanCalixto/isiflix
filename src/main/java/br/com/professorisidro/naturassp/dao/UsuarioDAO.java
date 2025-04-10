package br.com.professorisidro.naturassp.dao;

import br.com.professorisidro.naturassp.model.Usuario;
import org.springframework.data.repository.CrudRepository;

public interface UsuarioDAO extends CrudRepository<Usuario, Integer> {

    public Usuario findByUsernameOrEmail(String username, String email);
}
