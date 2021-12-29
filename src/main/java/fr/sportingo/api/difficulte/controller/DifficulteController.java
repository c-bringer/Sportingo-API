package fr.sportingo.api.difficulte.controller;

import fr.sportingo.api.difficulte.model.Difficulte;
import fr.sportingo.api.difficulte.service.DifficulteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
public class DifficulteController {
    @Autowired
    private DifficulteService difficulteService;

    @PostMapping("/admin/difficulte")
    public Difficulte saveDifficulte(@RequestBody Difficulte difficulte) {
        return difficulteService.saveDifficulte(difficulte);
    }

    @GetMapping("/difficulty")
    public Iterable<Difficulte> getDifficultes() {
        return difficulteService.getDifficultes();
    }

    @GetMapping("/difficulty/{id}")
    public Difficulte getDifficulte(@PathVariable("id") final Long id) {
        Optional<Difficulte> difficulte = difficulteService.getDifficulte(id);

        return difficulte.orElse(null);
    }

    @PutMapping("/admin/difficulty/{id}")
    public Difficulte updateDifficulte(@PathVariable("id") final Long id, @RequestBody Difficulte difficulte) {
        Optional<Difficulte> d = difficulteService.getDifficulte(id);

        if(d.isPresent()) {
            Difficulte currentDifficulte = d.get();

            String libelle = difficulte.getLibelle();
            if(libelle != null) {
                currentDifficulte.setLibelle(libelle);
            }

            difficulteService.saveDifficulte(currentDifficulte);
            return currentDifficulte;
        } else {
            return null;
        }
    }

    @DeleteMapping("/admin/difficulty/{id}")
    public void deleteDifficulty(@PathVariable("id") final Long id) {
        difficulteService.deleteDifficulte(id);
    }
}
