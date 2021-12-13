package fr.sportingo.api.evenement.controller;

import fr.sportingo.api.evenement.model.Evenement;
import fr.sportingo.api.evenement.model.EvenementCirculation;
import fr.sportingo.api.evenement.service.EvenementCirculationService;
import fr.sportingo.api.motifCirculation.model.MotifCirculation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
public class EvenementCirculationController {

    @Autowired
    private EvenementCirculationService circulationService;

    @PostMapping("/eventcircu")
    public EvenementCirculation enregistrerEvenementCirculation(@RequestBody EvenementCirculation evenement) {
        return circulationService.enregistrerEvenement(evenement);
    }

    @GetMapping("/eventcircu")
    public Iterable<EvenementCirculation> getEvenementsCirculation() {
        return circulationService.getEvenementsCirculation();
    }

    @GetMapping("/eventcircu/{id}")
    public EvenementCirculation getEvenementCirculation(@PathVariable("id") final Long id) {
        Optional<EvenementCirculation> evenement = circulationService.getEvenementCirculation(id);

        return evenement.orElse(null);
    }

    @GetMapping("/eventcircu/user/{idUser}")
    public Iterable<EvenementCirculation> getEvenementsCirculationByUtilisateur(@PathVariable("idUser") final Long idUtilisateur) {
        return circulationService.getEvenementsCirculationByUtilisateur(idUtilisateur);
    }

    @PutMapping("/eventcircu/{id}")
    public EvenementCirculation modifierEvenementCirculation(@RequestBody EvenementCirculation evenement, @PathVariable("id") final Long id) {
        Optional<EvenementCirculation> e = circulationService.getEvenementCirculation(id);

        if(e.isPresent()) {
            EvenementCirculation currentEvenement = e.get();

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

            MotifCirculation motifCirculation = evenement.getMotifCirculation();
            if(motifCirculation != null) {
                currentEvenement.setMotifCirculation(motifCirculation);
            }

            circulationService.enregistrerEvenement(currentEvenement);
            return currentEvenement;
        } else {
            return null;
        }
    }

    @DeleteMapping("/eventcircu/{id}")
    public void supprimerEvenementCirculation(@PathVariable("id") final Long id) {
        circulationService.supprimerEvenement(id);
    }
}
