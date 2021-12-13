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

    @PostMapping("/event")
    public Evenement enregistrerEvenement(@RequestBody Evenement evenement) {
        return evenementService.enregistrerEvenement(evenement);
    }

    @GetMapping("/event")
    public Iterable<Evenement> getEvenements() {
        return evenementService.getEvenements();
    }

    @GetMapping("/event/{id}")
    public Evenement getEvenement(@PathVariable("id") final Long id) {
        Optional<Evenement> evenement = evenementService.getEvenement(id);

        return evenement.orElse(null);
    }

    @GetMapping("/event/user/{idUser}")
    public Iterable<Evenement> getEvenementsByUtilisateur(@PathVariable("idUser") final Long idUtilisateur) {
        return evenementService.getEvenementsByUtilisateur(idUtilisateur);
    }

    @PutMapping("/event/{id}")
    public Evenement modifierEvenement(@RequestBody Evenement evenement, @PathVariable("id") final Long id) {
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

            evenementService.enregistrerEvenement(currentEvenement);
            return currentEvenement;
        } else {
            return null;
        }
    }

    @DeleteMapping("/event/{id}")
    public void supprimerEvenement(@PathVariable("id") final Long id) {
        evenementService.supprimerEvenement(id);
    }
}
