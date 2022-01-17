package fr.sportingo.api.difficulte.service;

import fr.sportingo.api.difficulte.model.Difficulte;
import fr.sportingo.api.difficulte.repository.DifficulteRepository;
import fr.sportingo.api.difficulte.statut.DifficulteStatut;
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

    public List<Difficulte> getDifficultesBystatut(DifficulteStatut statut) {
        return difficulteRepository.getDifficultesByStatut(statut);
    }

    public Difficulte saveDifficulte(Difficulte difficulte) {
        return difficulteRepository.save(difficulte);
    }
}
