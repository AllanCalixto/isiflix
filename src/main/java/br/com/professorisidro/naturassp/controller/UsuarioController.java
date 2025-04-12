package br.com.professorisidro.naturassp.controller;

import br.com.professorisidro.naturassp.model.Usuario;
import br.com.professorisidro.naturassp.security.JWTToken;
import br.com.professorisidro.naturassp.security.JWTTokenUtil;
import br.com.professorisidro.naturassp.service.IUsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UsuarioController {

    @Autowired
    private IUsuarioService service;

    @PostMapping("/login")
    public ResponseEntity<JWTToken> fazerLogin(@RequestBody Usuario dadosLogin) {
        Usuario user = service.recuperarUsuario(dadosLogin); // Valida o usuário no banco
        if (user != null && user.getSenha().equals(dadosLogin.getSenha())) {
            JWTToken jwtToken = new JWTToken();
            jwtToken.setToken(JWTTokenUtil.generateToken(user)); // Gera o token
            return ResponseEntity.ok(jwtToken); // Retorna o token como resposta
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build(); // Senha ou usuário inválido
    }
}

