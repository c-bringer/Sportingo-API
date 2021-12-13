package fr.sportingo.api.evenement.service;

import fr.sportingo.api.evenement.model.Evenement;
import fr.sportingo.api.evenement.model.EvenementSportif;
import fr.sportingo.api.evenement.repository.EvenementSportifRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class EvenementSportifService {

    @Autowired
    private EvenementSportifRepository sportifRepository;

    public Optional<EvenementSportif> getEvenementSportif(final Long id) {
        return sportifRepository.findById(id);
    }

    public Iterable<EvenementSportif> getEvenementsSportif() {
        return sportifRepository.findAll();
    }

    public Iterable<EvenementSportif> getEvenementsSportifByUtilisateur(final Long idUtilisateur) {
        return sportifRepository.getEvenementsSportifByUtilisateur(idUtilisateur);
    }

    public Iterable<EvenementSportif> getEvenementsSportifBySportMecanique(final Long idSportMecanique) {
        return sportifRepository.getEvenementsSportifBySportMecanique(idSportMecanique);
    }

    public void supprimerEvenement(final Long id) {
        sportifRepository.deleteById(id);
    }

    public EvenementSportif enregistrerEvenement(EvenementSportif evenementSportif) {
        return sportifRepository.save(evenementSportif);
    }
}
