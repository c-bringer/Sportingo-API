package fr.sportingo.api.motifCirculation.exception;

public class MotifCirculationNotFoundException extends RuntimeException {
    public MotifCirculationNotFoundException(Long id) {
        super("Impossible de trouver le motif de circulation " + id);
    }
}
