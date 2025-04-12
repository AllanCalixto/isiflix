package br.com.professorisidro.naturassp.security;
public class JWTToken {

    private String token;

    public JWTToken() {}

    public JWTToken(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    @Override
    public String toString() {
        return "JWTToken{" +
                "token='" + token + '\'' +
                '}';
    }
}
