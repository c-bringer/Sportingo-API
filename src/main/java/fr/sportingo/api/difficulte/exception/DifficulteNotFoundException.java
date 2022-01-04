package fr.sportingo.api.difficulte.exception;

public class DifficulteNotFoundException extends RuntimeException {
    public DifficulteNotFoundException(Long id) {
        super("Impossible de trouver la difficulté " + id);
    }
}
