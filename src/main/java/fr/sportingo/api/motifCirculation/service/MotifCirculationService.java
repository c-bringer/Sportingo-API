package fr.sportingo.api.motifCirculation.service;

import fr.sportingo.api.motifCirculation.model.MotifCirculation;
import fr.sportingo.api.motifCirculation.repository.MotifCirculationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MotifCirculationService {

    @Autowired
    private MotifCirculationRepository motifCirculationRepository;

    public Optional<MotifCirculation> getMotifCirculation(final Long id) {
        return motifCirculationRepository.findById(id);
    }

    public Iterable<MotifCirculation> getMotifsCirculation() {
        return motifCirculationRepository.findAll();
    }

    public void deleteMotifCirculation(final Long id) {
        motifCirculationRepository.deleteById(id);
    }

    public MotifCirculation saveMotifCirculation(MotifCirculation motifCirculation) {
        return motifCirculationRepository.save(motifCirculation);
    }
}
