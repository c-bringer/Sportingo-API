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

    @PostMapping("/admin/mechanical-sport")
    public SportMecanique saveSportMecanique(@RequestBody SportMecanique sportMecanique) {
        return sportMecaniqueService.saveSportMecanique(sportMecanique);
    }

    @GetMapping("/mechanical-sport")
    public Iterable<SportMecanique> getMechanicalsSports() {
        return sportMecaniqueService.getSportsMecaniques();
    }

    @GetMapping("/mechanical-sport/{id}")
    public SportMecanique getSportMecanique(@PathVariable("id") final Long id) {
        Optional<SportMecanique> sportMecanique = sportMecaniqueService.getSportMecanique(id);

        return sportMecanique.orElse(null);
    }

    @PutMapping("/admin/mechanical-sport/{id}")
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

    @DeleteMapping("/admin/mechanical-sport/{id}")
    public void deleteSportMecanique(@PathVariable("id") final Long id) {
        sportMecaniqueService.deleteSportMecanique(id);
    }
}
