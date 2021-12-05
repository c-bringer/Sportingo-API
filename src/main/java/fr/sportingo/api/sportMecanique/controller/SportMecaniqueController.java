package fr.sportingo.api.sportMecanique.controller;

import fr.sportingo.api.sportMecanique.service.SportMecaniqueService;
import fr.sportingo.api.sportMecanique.model.SportMecanique;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

/**
 * RestController pour les sports mecaniques
 * @author Corentin Bringer
 * @version 1.0
 */
@RestController
public class SportMecaniqueController
{
    @Autowired
    private SportMecaniqueService sportMecaniqueService;


    /**
     * Create - Ajoute un nouveau sport mécanique
     * @param sportMecanique Objet SportMecanique
     * @return Objet SportMecanique
     */
    @PostMapping("/admin/mechanical-sport")
    public SportMecanique enregistrerSportMecanique(@RequestBody SportMecanique sportMecanique)
    {
        return sportMecaniqueService.enregistrerSportMecanique(sportMecanique);
    }


    /**
     * Read - Récupère tous les sports mécaniques
     * @return Objet SportMecanique
     */
    @GetMapping("/mechanical-sport")
    public Iterable<SportMecanique> getMechanicalsSports()
    {
        return sportMecaniqueService.getSportsMecaniques();
    }


    /**
     * Read - Récupère un sport mécanique en fonction de l'id
     * @param id Long id
     * @return MechanicalSport || null
     */
    @GetMapping("/mechanical-sport/{id}")
    public SportMecanique getSportMecanique(@PathVariable("id") final Long id)
    {
        Optional<SportMecanique> sportMecanique = sportMecaniqueService.getSportMecanique(id);

        return sportMecanique.orElse(null);
    }


    /**
     * Update - Mets à jour le sport mécanique en fonction de l'id
     * @param id Integer id
     * @param sportMecanique Objet SportMecanique
     * @return Objet SportMecanique || null
     */
    @PutMapping("/admin/mechanical-sport/{id}")
    public SportMecanique modifierSportMecanique(@PathVariable("id") final Long id, @RequestBody SportMecanique sportMecanique)
    {
        Optional<SportMecanique> sm = sportMecaniqueService.getSportMecanique(id);

        if(sm.isPresent()) {
            SportMecanique currentSportMecanique = sm.get();

            String libelle = sportMecanique.getLibelle();
            if(libelle != null) {
                currentSportMecanique.setLibelle(libelle);
            }

            sportMecaniqueService.enregistrerSportMecanique(currentSportMecanique);
            return currentSportMecanique;
        } else {
            return null;
        }
    }


    /**
     * Delete - Supprime un sport mécanique en fonction de l'id
     * @param id Integer id
     */
    @DeleteMapping("/admin/mechanical-sport/{id}")
    public void supprimerSportMecanique(@PathVariable("id") final Long id)
    {
        sportMecaniqueService.supprimerSportMecanique(id);
    }
}
