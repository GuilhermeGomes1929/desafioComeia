package com.vacina.agenda.model;

public class JwtResponse {
    private final String jwToken;

    public JwtResponse(String jwToken) {
        this.jwToken = jwToken;
    }

    public String getJwToken() {
        return jwToken;
    }
}
