package fr.sportingo.api.security;

import lombok.Getter;

@Getter
public class AuthenticationResponse {
    private final String jwt;

    public AuthenticationResponse(String jwt) {
        super();
        this.jwt = jwt;
    }
}
