package fr.sportingo.api.difficulte.service;

import fr.sportingo.api.difficulte.model.Difficulte;
import fr.sportingo.api.difficulte.repository.DifficulteRepository;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

//@Service
public class DifficulteService {
    @Autowired
    private DifficulteRepository difficulteRepository;

    public Optional<Difficulte> getDifficulte(final Long id) {
        return difficulteRepository.findById(id);
    }

    public Iterable<Difficulte> getDifficultes() {
        return difficulteRepository.findAll();
    }

    public void supprimmerDifficulte(final Long id) {
        difficulteRepository.deleteById(id);
    }

    public Difficulte enregistreDifficulte(Difficulte difficulte) {
        return difficulteRepository.save(difficulte);
    }
}
