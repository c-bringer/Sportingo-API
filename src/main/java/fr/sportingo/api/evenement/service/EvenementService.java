package fr.sportingo.api.evenement.service;

import fr.sportingo.api.evenement.model.Evenement;
import fr.sportingo.api.evenement.repository.EvenementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class EvenementService
{
    @Autowired
    private EvenementRepository evenementRepository;

    public Optional<Evenement> getEvenement(final Long id) {
        return evenementRepository.findById(id);
    }

    public Iterable<Evenement> getEvenements() {
        return evenementRepository.findAll();
    }

    public Iterable<Evenement> getEvenementsByUtilisateur(final Long idUtilisateur) {
        return evenementRepository.getEvenementsByUtilisateur(idUtilisateur);
    }

    public void supprimerEvenement(final Long id) {
        evenementRepository.deleteById(id);
    }

    public Evenement enregistrerEvenement(Evenement evenement) {
        return evenementRepository.save(evenement);
    }
}
