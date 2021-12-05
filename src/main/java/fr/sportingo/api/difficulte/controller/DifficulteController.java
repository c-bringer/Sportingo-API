package fr.sportingo.api.difficulte.controller;

import fr.sportingo.api.difficulte.model.Difficulte;
import fr.sportingo.api.difficulte.service.DifficulteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

/**
 * RestController pour les difficultés
 * @author Corentin Bringer
 * @version 1.0
 */
@RestController
public class DifficulteController
{
    @Autowired
    private DifficulteService difficulteService;


    /**
     * Create - Enregistre une nouvelle difficulté
     * @param difficulte Objet Difficulte
     * @return Objet Difficulte
     */
    @PostMapping("/admin/difficulte")
    public Difficulte enregistreDifficulte(@RequestBody Difficulte difficulte)
    {
        return difficulteService.enregistreDifficulte(difficulte);
    }


    /**
     * Read - Retourne la liste de toutes les difficultés
     * @return Objet Difficulte
     */
    @GetMapping("/difficulty")
    public Iterable<Difficulte> getDifficultes()
    {
        return difficulteService.getDifficultes();
    }


    /**
     * Read - Retourne une difficulté en fonction de l'od
     * @param id Integer id
     * @return Objet Difficulte
     */
    @GetMapping("/difficulty/{id}")
    public Difficulte getDifficulte(@PathVariable("id") final Long id)
    {
        Optional<Difficulte> difficulte = difficulteService.getDifficulte(id);

        return difficulte.orElse(null);
    }


    /**
     * Update - Mets à jour une difficulté en fonction de l'id
     * @param id Integer id
     * @param difficulte Objet Difficulte
     * @return Objet Difficulte
     */
    @PutMapping("/admin/difficulty/{id}")
    public Difficulte modifierDifficulte(@PathVariable("id") final Long id, @RequestBody Difficulte difficulte)
    {
        Optional<Difficulte> d = difficulteService.getDifficulte(id);

        if(d.isPresent()) {
            Difficulte currentDifficulte = d.get();

            String libelle = difficulte.getLibelle();
            if(libelle != null) {
                currentDifficulte.setLibelle(libelle);
            }

            difficulteService.enregistreDifficulte(currentDifficulte);
            return currentDifficulte;
        } else {
            return null;
        }
    }


    /**
     * Delete - Supprime une difficulté en fonction de l'id
     * @param id Integer id
     */
    @DeleteMapping("/admin/difficulty/{id}")
    public void deleteDifficulty(@PathVariable("id") final Long id)
    {
        difficulteService.supprimmerDifficulte(id);
    }
}
