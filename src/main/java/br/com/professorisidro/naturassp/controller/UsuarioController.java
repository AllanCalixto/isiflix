package br.com.professorisidro.naturassp.controller;

import br.com.professorisidro.naturassp.model.Usuario;
import br.com.professorisidro.naturassp.security.JWTToken;
import br.com.professorisidro.naturassp.security.JWTTokenUtil;
import br.com.professorisidro.naturassp.service.IUsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UsuarioController {

    @Autowired
    private IUsuarioService service;

    @PostMapping("/login")
    public ResponseEntity<JWTToken> fazerLogin (@RequestBody Usuario dadosLogin) {
        System.out.println("DADOS: "+ dadosLogin);
        Usuario user = service.recuperarUsuario(dadosLogin);
        if (user != null) { // Usuário existente, precisa conferir a senha
            if(user.getSenha().equals(dadosLogin.getSenha())) {
                JWTToken jwtToken = new JWTToken();
                jwtToken.setToken(JWTTokenUtil.generateToken(user));
                return ResponseEntity.ok(jwtToken);
            }
            return ResponseEntity.status(403).build(); // Usuário não tem autorização
        }
        return ResponseEntity.notFound().build(); // Usuário não encontrado.
    }
}
