package fr.sportingo.api.sportMecanique.service;

import fr.sportingo.api.sportMecanique.model.SportMecanique;
import fr.sportingo.api.sportMecanique.repository.SportMecaniqueRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

//@Service
public class SportMecaniqueService {
    @Autowired
    private SportMecaniqueRepository sportMecaniqueRepository;

    public Optional<SportMecanique> getSportMecanique(final Long id) {
        return sportMecaniqueRepository.findById(id);
    }

    public Iterable<SportMecanique> getSportsMecaniques() {
        return sportMecaniqueRepository.findAll();
    }

    public void supprimerSportMecanique(final Long id) {
        sportMecaniqueRepository.deleteById(id);
    }

    public SportMecanique enregistrerSportMecanique(SportMecanique sportMecanique) {
        return sportMecaniqueRepository.save(sportMecanique);
    }
}
