package fr.sportingo.api.utilisateur.exception;

import java.util.UUID;

public class UtilisateurNotFoundException extends RuntimeException {
    public UtilisateurNotFoundException(UUID uuid) {
        super("Impossible de trouver l'utilisateur " + uuid);
    }
}
