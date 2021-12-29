package fr.sportingo.api.magasin.controller;

import fr.sportingo.api.magasin.model.Magasin;
import fr.sportingo.api.magasin.service.MagasinService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
public class MagasinController {

    @Autowired
    private MagasinService magasinService;

    @PostMapping("/magasin")
    public Magasin saveMagasin(@RequestBody Magasin magasin) {
        return magasinService.saveMagasin(magasin);
    }

    @GetMapping("/magasin")
    public Iterable<Magasin> getMagasins() {
        return magasinService.getMagasins();
    }

    @GetMapping("/magasin/{id}")
    public Magasin getMagasin(@PathVariable("id") final Long id) {
        Optional<Magasin> magasin = magasinService.getMagasin(id);

        return magasin.orElse(null);
    }

    @GetMapping("/magasin/user/{idUser}")
    public Iterable<Magasin> getMagasinsByUtilisateur(@PathVariable("idUser") final Long idUtilisateur) {
        return magasinService.getMagasinsByUtilisateur(idUtilisateur);
    }

    @PutMapping("/magasin/{id}")
    public Magasin updateMagasin(@RequestBody Magasin magasin, @PathVariable("id") final Long id) {
        Optional<Magasin> m = magasinService.getMagasin(id);

        if(m.isPresent()) {
            Magasin currentMagasin = m.get();

            String nom = magasin.getNom();
            if(nom != null) {
                currentMagasin.setNom(nom);
            }

            String adresse = magasin.getAdresse();
            if(adresse != null) {
                currentMagasin.setAdresse(adresse);
            }

            String codePostal = magasin.getCodePostal();
            if(codePostal != null) {
                currentMagasin.setCodePostal(codePostal);
            }

            String ville = magasin.getVille();
            if(ville != null) {
                currentMagasin.setVille(ville);
            }

            magasinService.saveMagasin(currentMagasin);
            return currentMagasin;
        } else {
            return null;
        }
    }

    @DeleteMapping("/magasin/{id}")
    public void deleteMagasin(@PathVariable("id") final Long id) {
        magasinService.deleteMagasin(id);
    }
}
