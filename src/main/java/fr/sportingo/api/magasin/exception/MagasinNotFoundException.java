package fr.sportingo.api.magasin.exception;

public class MagasinNotFoundException extends RuntimeException {
    public MagasinNotFoundException(Long id) {
        super("Impossible de trouver le magasin " + id);
    }
}
