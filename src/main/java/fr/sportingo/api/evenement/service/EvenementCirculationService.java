package fr.sportingo.api.evenement.service;

import fr.sportingo.api.evenement.model.EvenementCirculation;
import fr.sportingo.api.evenement.repository.EvenementCirculationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class EvenementCirculationService {

    @Autowired
    private EvenementCirculationRepository circulationRepository;

    public Optional<EvenementCirculation> getEvenementCirculation(final Long id) {
        return circulationRepository.findById(id);
    }

    public Iterable<EvenementCirculation> getEvenementsCirculation() {
        return circulationRepository.findAll();
    }

    public Iterable<EvenementCirculation> getEvenementsCirculationByUtilisateur(final Long idUtilisateur) {
        return circulationRepository.getEvenementsCirculationByUtilisateur(idUtilisateur);
    }

    public void deleteEvenement(final Long id) {
        circulationRepository.deleteById(id);
    }

    public EvenementCirculation saveEvenement(EvenementCirculation evenementCirculation) {
        return circulationRepository.save(evenementCirculation);
    }
}
