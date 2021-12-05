package fr.sportingo.api.controller;

import fr.sportingo.api.model.Difficulty;
import fr.sportingo.api.service.DifficultyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

/**
 * RestController pour les difficulty
 * @author Corentin Bringer
 * @version 1.0
 */
@RestController
public class DifficultyController
{
    @Autowired
    private DifficultyService difficultyService;


    /**
     * Create - Enregistre une nouvelle difficulté
     * @param difficulty Objet Difficulty
     * @return Objet Difficulty
     */
    @PostMapping("/admin/difficulty")
    public Difficulty saveDifficulty(@RequestBody Difficulty difficulty)
    {
        return difficultyService.saveDifficulty(difficulty);
    }


    /**
     * Read - Retourne la liste de toutes les difficultés
     * @return Objet Difficulty
     */
    @GetMapping("/difficulty")
    public Iterable<Difficulty> getDifficulties()
    {
        return difficultyService.getDifficulties();
    }


    /**
     * Read - Retourne une difficulté en fonction de l'od
     * @param id Integer id
     * @return Objet Difficulty
     */
    @GetMapping("/difficulty/{id}")
    public Difficulty getDifficulty(@PathVariable("id") final Long id)
    {
        Optional<Difficulty> difficulty = difficultyService.getDifficulty(id);

        return difficulty.orElse(null);
    }


    /**
     * Update - Mets à jour une difficulté en fonction de l'id
     * @param id Integer id
     * @param difficulty Objet Difficulty
     * @return Objet Difficulty
     */
    @PutMapping("/admin/difficulty/{id}")
    public Difficulty updateDifficulty(@PathVariable("id") final Long id, @RequestBody Difficulty difficulty)
    {
        Optional<Difficulty> d = difficultyService.getDifficulty(id);

        if(d.isPresent()) {
            Difficulty currentDifficulty = d.get();

            String label = difficulty.getLabel();
            if(label != null) {
                currentDifficulty.setLabel(label);
            }

            difficultyService.saveDifficulty(currentDifficulty);
            return currentDifficulty;
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
        difficultyService.deleteDifficulty(id);
    }
}
