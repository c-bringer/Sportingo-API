package fr.sportingo.api.evenement.controller;

import fr.sportingo.api.evenement.model.Evenement;
import fr.sportingo.api.evenement.service.EvenementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
public class EvenementController
{
    @Autowired
    private EvenementService evenementService;

    @PostMapping("/private/evenement/ajouter")
    public Evenement saveEvenement(@RequestBody Evenement evenement) {
        return evenementService.saveEvenement(evenement);
    }

    @GetMapping("/public/evenement/liste-evenement")
    public Iterable<Evenement> getEvenements() {
        return evenementService.getEvenements();
    }

    @GetMapping("/public/evenement/{id}")
    public Evenement getEvenement(@PathVariable("id") final Long id) {
        Optional<Evenement> evenement = evenementService.getEvenement(id);

        return evenement.orElse(null);
    }

    @GetMapping("/public/evenement/utilisateur/{idUser}")
    public Iterable<Evenement> getEvenementsByUtilisateur(@PathVariable("idUser") final Long idUtilisateur) {
        return evenementService.getEvenementsByUtilisateur(idUtilisateur);
    }

    @PutMapping("/private/evenement/modifier/{id}")
    public Evenement updateEvenement(@RequestBody Evenement evenement, @PathVariable("id") final Long id) {
        Optional<Evenement> e = evenementService.getEvenement(id);

        if(e.isPresent()) {
            Evenement currentEvenement = e.get();

            String image = evenement.getImage();
            if(image != null) {
                currentEvenement.setImage(image);
            }

            String nom = evenement.getNom();
            if(nom != null) {
                currentEvenement.setNom(nom);
            }

            String description = evenement.getDescription();
            if(description != null) {
                currentEvenement.setDescription(description);
            }

            Double longitude = evenement.getLongitude();
            if(longitude != null) {
                currentEvenement.setLongitude(longitude);
            }

            Double latitude = evenement.getLatitude();
            if(latitude != null) {
                currentEvenement.setLatitude(latitude);
            }

            Integer isVerifie = evenement.getIsVerifie();
            if(isVerifie != null) {
                currentEvenement.setIsVerifie(isVerifie);
            }

            Integer isDesactive = evenement.getIsDesactive();
            if(isDesactive != null) {
                currentEvenement.setIsDesactive(isDesactive);
            }

            evenementService.saveEvenement(currentEvenement);
            return currentEvenement;
        } else {
            return null;
        }
    }

    @DeleteMapping("/private/evenement/supprimer/{id}")
    public void deleteEvenement(@PathVariable("id") final Long id) {
        evenementService.deleteEvenement(id);
    }
}
