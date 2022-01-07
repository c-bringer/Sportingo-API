package fr.sportingo.api.difficulte.service;

import fr.sportingo.api.difficulte.model.Difficulte;
import fr.sportingo.api.difficulte.repository.DifficulteRepository;
import fr.sportingo.api.difficulte.status.DifficulteStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DifficulteService {
    @Autowired
    private DifficulteRepository difficulteRepository;

    public Optional<Difficulte> getDifficulte(final Long id) {
        return difficulteRepository.findById(id);
    }

    public List<Difficulte> getDifficultes() {
        return difficulteRepository.findAll();
    }

    public List<Difficulte> getDifficultesActivees(DifficulteStatus status) {
        return difficulteRepository.getDifficultesActivees(status);
    }

    public List<Difficulte> getDifficultesDesactivees(DifficulteStatus status) {
        return difficulteRepository.getDifficultesDesactivees(status);
    }

    public void deleteDifficulte(final Long id) {
        difficulteRepository.deleteById(id);
    }

    public Difficulte saveDifficulte(Difficulte difficulte) {
        return difficulteRepository.save(difficulte);
    }
}
