package fr.sportingo.api.sportMecanique.exception;

public class SportMecaniqueNotFoundException extends RuntimeException {
    public SportMecaniqueNotFoundException(Long id) {
        super("Impossible de trouver le sport mécanique " + id);
    }
}