package fr.sportingo.api.evenement.controller;

import fr.sportingo.api.evenement.model.Evenement;
import fr.sportingo.api.evenement.service.EvenementService;
import fr.sportingo.api.sportMecanique.model.SportMecanique;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.Optional;

/**
 * RestController pour les evenements
 * @author Corentin Bringer
 * @version 1.0
 */
@RestController
public class EvenementController
{
    @Autowired
    private EvenementService evenementService;


    /**
     * Create - Enregistre un nouvel evenement
     * @param evenement Objet Evenement
     * @return Objet Evenement
     */
    @PostMapping("/event")
    public Evenement enregistrerEvenement(@RequestBody Evenement evenement)
    {
        return evenementService.enregistrerEvenement(evenement);
    }


    /**
     * Read - Retourne tous les evenements
     * @return Objet Evenement
     */
    @GetMapping("/event")
    public Iterable<Evenement> getEvenements()
    {
        return evenementService.getEvenements();
    }


    /**
     * Read - Retourne un evenement en fonction de l'id
     * @param id Integer id
     * @return Objet Evenement
     */
    @GetMapping("/event/{id}")
    public Evenement getEvent(@PathVariable("id") final Long id)
    {
        Optional<Evenement> evenement = evenementService.getEvenement(id);

        return evenement.orElse(null);
    }


    /**
     * Read - Retourne la liste des evenements publie par un utilsateur
     * @param idUtilisateur Integer idUtilisateur
     * @return Objet Evenement
     */
    @GetMapping("/event/user/{idUser}")
    public Iterable<Evenement> getEvenementParUtilisateur(@PathVariable("idUser") final Long idUtilisateur)
    {
        return evenementService.getEvenementParUtilisateur(idUtilisateur);
    }


    /**
     * Read - Retourne la liste des evenements en fonction d'un sport mecanique
     * @param idSportMecanique Integer idSportMecanique
     * @return Objet Evenement
     */
    @GetMapping("/event/mechanical-sport/{idMechanicalSport}")
    public Iterable<Evenement> getEvenementParSportMecanique(@PathVariable("idMechanicalSport") final Long idSportMecanique)
    {
        return evenementService.getEvenementParSportMecanique(idSportMecanique);
    }


    /**
     * Update - Mets à jour un evenement en fonction de l'id
     * @param evenement Objet Evenement
     * @param id Integer id
     * @return Objet Evenement
     */
    @PutMapping("/event/{id}")
    public Evenement modifierEvenement(@RequestBody Evenement evenement, @PathVariable("id") final Long id)
    {
        Optional<Evenement> e = evenementService.getEvenement(id);

        if(e.isPresent()) {
            Evenement currentEvenement = e.get();

            String image = evenement.getImage();
            if(image != null) {
                currentEvenement.setImage(image);
            }

            String name = evenement.getName();
            if(name != null) {
                currentEvenement.setName(name);
            }

            String description = evenement.getDescription();
            if(description != null) {
                currentEvenement.setDescription(description);
            }

            Date dateHours = evenement.getDateHours();
            if(dateHours != null) {
                currentEvenement.setDateHours(dateHours);
            }

            Date minimumBirthday = evenement.getMinimumBirthday();
            if(minimumBirthday != null) {
                currentEvenement.setMinimumBirthday(minimumBirthday);
            }

            Integer nbPlaces = evenement.getNbPlaces();
            if(nbPlaces != null) {
                currentEvenement.setNbPlaces(nbPlaces);
            }

            Double longitude = evenement.getLongitude();
            if(longitude != null) {
                currentEvenement.setLongitude(longitude);
            }

            Double latitude = evenement.getLatitude();
            if(latitude != null) {
                currentEvenement.setLatitude(latitude);
            }

            Integer isVerified = evenement.getIsVerified();
            if(isVerified != null) {
                currentEvenement.setIsVerified(isVerified);
            }

            Integer isDisabled = evenement.getIsDisabled();
            if(isDisabled != null) {
                currentEvenement.setIsDisabled(isDisabled);
            }

            SportMecanique sportMecanique = evenement.getSportMecanique();
            if(sportMecanique != null) {
                currentEvenement.setSportMecanique(sportMecanique);
            }

//            List<Category> categories = event.getCategories();
//            if(categories != null) {
//                currentEvent.setCategories(categories);
//            }

            evenementService.enregistrerEvenement(currentEvenement);
            return currentEvenement;
        } else {
            return null;
        }
    }


    /**
     * Delete - Supprime un evenement en fonction de l'id
     * @param id Integer id
     */
    @DeleteMapping("/event/{id}")
    public void supprimerEvenement(@PathVariable("id") final Long id)
    {
        evenementService.supprimerEvenement(id);
    }
}
