package fr.sportingo.api.magasin.exception;

import java.util.UUID;

public class MagasinNotFoundException extends RuntimeException {
    public MagasinNotFoundException(UUID uuid) {
        super("Impossible de trouver le magasin " + uuid);
    }
}
