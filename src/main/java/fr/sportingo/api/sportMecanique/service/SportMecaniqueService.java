package fr.sportingo.api.sportMecanique.service;

import fr.sportingo.api.sportMecanique.model.SportMecanique;
import fr.sportingo.api.sportMecanique.repository.SportMecaniqueRepository;
import fr.sportingo.api.sportMecanique.status.SportMecaniqueStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SportMecaniqueService {
    @Autowired
    private SportMecaniqueRepository sportMecaniqueRepository;

    public Optional<SportMecanique> getSportMecanique(final Long id) {
        return sportMecaniqueRepository.findById(id);
    }

    public List<SportMecanique> getSportsMecaniques() {
        return sportMecaniqueRepository.findAll();
    }

    public List<SportMecanique> getSportMecaniquesByStatus(SportMecaniqueStatus status) {
        return sportMecaniqueRepository.getSportMecaniquesByStatus(status);
    }

    public SportMecanique saveSportMecanique(SportMecanique sportMecanique) {
        return sportMecaniqueRepository.save(sportMecanique);
    }
}
