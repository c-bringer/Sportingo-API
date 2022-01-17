package fr.sportingo.api.motifCirculation.service;

import fr.sportingo.api.motifCirculation.model.MotifCirculation;
import fr.sportingo.api.motifCirculation.repository.MotifCirculationRepository;
import fr.sportingo.api.motifCirculation.statut.MotifCirculationStatut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MotifCirculationService {

    @Autowired
    private MotifCirculationRepository motifCirculationRepository;

    public Optional<MotifCirculation> getMotifCirculation(final Long id) {
        return motifCirculationRepository.findById(id);
    }

    public List<MotifCirculation> getMotifsCirculation() {
        return motifCirculationRepository.findAll();
    }

    public List<MotifCirculation> getMotifsCirculationByStatut(MotifCirculationStatut statut) {
        return motifCirculationRepository.getMotifsCirculationByStatut(statut);
    }

    public MotifCirculation saveMotifCirculation(MotifCirculation motifCirculation) {
        return motifCirculationRepository.save(motifCirculation);
    }
}
