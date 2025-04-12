package br.com.professorisidro.naturassp.security;

import br.com.professorisidro.naturassp.model.Usuario;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;

import java.security.Key;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;

public class JWTTokenUtil {

    private static final String SECRET_KEY = "N@tur@55pW3bS3cur1tyT0k3n202101234567890"; // Chave atualizada para ter 256 bits
    private static final int EXPIRATION = 2 * 60 * 1000; // Expiração em milissegundos

    public static String generateToken(Usuario usuario) {
        Key secretKey = Keys.hmacShaKeyFor(SECRET_KEY.getBytes());
        return Jwts.builder()
                .setSubject(usuario.getUsername()) // Identificação do usuário
                .setIssuer("*Professorisidro*") // Emissor do token
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION)) // Expiração
                .signWith(secretKey, SignatureAlgorithm.HS256) // Algoritmo de assinatura
                .compact();
    }

    public static Authentication decodeToken(HttpServletRequest request) {
        try {
            String token = request.getHeader("Authorization").replace("Bearer", "").trim();
            Jws<Claims> claimsJws = Jwts.parserBuilder()
                    .setSigningKey(Keys.hmacShaKeyFor(SECRET_KEY.getBytes())) // Chave de assinatura
                    .build()
                    .parseClaimsJws(token);

            String username = claimsJws.getBody().getSubject();
            String issuer = claimsJws.getBody().getIssuer();
            Date expiration = claimsJws.getBody().getExpiration();

            if (isIssuerValid(issuer) && isSubjectValid(username) && isExpirationValid(expiration)) {
                return new UsernamePasswordAuthenticationToken(username, null, Collections.emptyList());
            }
        } catch (Exception e) {
            e.printStackTrace(); // Log para depuração
        }
        return null; // Retorna null em caso de falha na validação
    }

    private static boolean isIssuerValid(String issuer) {
        return "*Professorisidro*".equals(issuer);
    }

    private static boolean isSubjectValid(String subject) {
        return subject != null && !subject.isEmpty();
    }

    private static boolean isExpirationValid(Date expiration) {
        return expiration.after(new Date(System.currentTimeMillis()));
    }
}
