package fr.sportingo.api.sportMecanique.controller;

import fr.sportingo.api.sportMecanique.service.SportMecaniqueService;
import fr.sportingo.api.sportMecanique.model.SportMecanique;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
public class SportMecaniqueController {
    @Autowired
    private SportMecaniqueService sportMecaniqueService;

    @PostMapping("/private-scoped/admin/sport-mecanique/ajouter")
    public SportMecanique saveSportMecanique(@RequestBody SportMecanique sportMecanique) {
        return sportMecaniqueService.saveSportMecanique(sportMecanique);
    }

    @GetMapping("/public/sport-mecanique/liste-sport-mecanique")
    public Iterable<SportMecanique> getMechanicalsSports() {
        return sportMecaniqueService.getSportsMecaniques();
    }

    @GetMapping("/public/sport-mecanique/{id}")
    public SportMecanique getSportMecanique(@PathVariable("id") final Long id) {
        Optional<SportMecanique> sportMecanique = sportMecaniqueService.getSportMecanique(id);

        return sportMecanique.orElse(null);
    }

    @PutMapping("/private-scoped/admin/sport-mecanique/modifier/{id}")
    public SportMecanique updateSportMecanique(@PathVariable("id") final Long id, @RequestBody SportMecanique sportMecanique) {
        Optional<SportMecanique> sm = sportMecaniqueService.getSportMecanique(id);

        if(sm.isPresent()) {
            SportMecanique currentSportMecanique = sm.get();

            String libelle = sportMecanique.getLibelle();
            if(libelle != null) {
                currentSportMecanique.setLibelle(libelle);
            }

            sportMecaniqueService.saveSportMecanique(currentSportMecanique);
            return currentSportMecanique;
        } else {
            return null;
        }
    }

    @DeleteMapping("/private-scoped/admin/sport-mecanique/supprimer/{id}")
    public void deleteSportMecanique(@PathVariable("id") final Long id) {
        sportMecaniqueService.deleteSportMecanique(id);
    }
}
