package br.com.professorisidro.naturassp.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

public class TokenFilter extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        /**
         * Requisições que não precisam de autenticação, não levam em conta um item do cabeçalho:
         *   -- Authorization --
         *
         * Requisições que precisam de autenticação, vão precisar de uma informação de autorização do cabeçalho:
         * -- Se o token for válido, eu monto um cabeçalho de autorização e encaminho a requisição. --
         *
         * **/
        if (request.getHeader("Authorization") != null) {
            // Se eu tiver um cabeçalho com token, preciso decodificar este token.
            Authentication auth = JWTTokenUtil.decodeToken(request);

            // Se for válido, vai para o contexto da requisição um objeto que representa o token
            // Senão vai null
            SecurityContextHolder.getContext().setAuthentication(auth);
        }
        filterChain.doFilter(request, response);
    }
}
