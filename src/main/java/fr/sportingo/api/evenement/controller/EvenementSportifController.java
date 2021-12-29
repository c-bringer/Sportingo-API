package fr.sportingo.api.evenement.controller;

import fr.sportingo.api.evenement.model.Evenement;
import fr.sportingo.api.evenement.model.EvenementSportif;
import fr.sportingo.api.evenement.service.EvenementSportifService;
import fr.sportingo.api.sportMecanique.model.SportMecanique;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
public class EvenementSportifController {

    @Autowired
    private EvenementSportifService sportifService;

    @PostMapping("/eventsportif")
    public EvenementSportif saveEvenementSportif(@RequestBody EvenementSportif evenement) {
        return sportifService.saveEvenement(evenement);
    }

    @GetMapping("/eventsport")
    public Iterable<EvenementSportif> getEvenementsSportif() {
        return sportifService.getEvenementsSportif();
    }

    @GetMapping("/eventsport/{id}")
    public EvenementSportif getEvenementSportif(@PathVariable("id") final Long id) {
        Optional<EvenementSportif> evenement = sportifService.getEvenementSportif(id);

        return evenement.orElse(null);
    }

    @GetMapping("/eventsport/user/{idUser}")
    public Iterable<EvenementSportif> getEvenementsSportifByUtilisateur(@PathVariable("idUser") final Long idUtilisateur) {
        return sportifService.getEvenementsSportifByUtilisateur(idUtilisateur);
    }

    @PutMapping("/eventsport/{id}")
    public EvenementSportif updateEvenementSportif(@RequestBody EvenementSportif evenement, @PathVariable("id") final Long id) {
        Optional<EvenementSportif> e = sportifService.getEvenementSportif(id);

        if(e.isPresent()) {
            EvenementSportif currentEvenement = e.get();

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

            Integer nombrePlaces = evenement.getNombrePlaces();
            if(nombrePlaces != null) {
                currentEvenement.setNombrePlaces(nombrePlaces);
            }

            SportMecanique sportMecanique = evenement.getSportMecanique();
            if(sportMecanique != null) {
                currentEvenement.setSportMecanique(sportMecanique);
            }

            sportifService.saveEvenement(currentEvenement);
            return currentEvenement;
        } else {
            return null;
        }
    }

    @DeleteMapping("/eventsport/{id}")
    public void deleteEvenementSportif(@PathVariable("id") final Long id) {
        sportifService.deleteEvenement(id);
    }
}
