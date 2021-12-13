package fr.sportingo.api.spot.controller;

import fr.sportingo.api.difficulte.model.Difficulte;
import fr.sportingo.api.sportMecanique.model.SportMecanique;
import fr.sportingo.api.spot.service.SpotService;
import fr.sportingo.api.spot.model.Spot;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class SpotController {

    @Autowired
    private SpotService spotService;

    @PostMapping("/spot")
    public Spot enregistrerSpot(@RequestBody Spot spot) {
        return spotService.enregistrerSpot(spot);
    }

    @GetMapping("/spot/{id}")
    public Spot getSpot(@PathVariable("id") final Long id) {
        Optional<Spot> spot = spotService.getSpot(id);

        return spot.orElse(null);
    }

    @GetMapping("/spot")
    public Iterable<Spot> getSpots() {
        return spotService.getSpots();
    }

    @GetMapping("/spot/user/{id}")
    public Iterable<Spot> getSpotsByUtilisateur(@PathVariable("id") final Long idUtilisateur) {
        return spotService.getSpotsByUtilisateur(idUtilisateur);
    }

    @GetMapping("/spot/difficulty/{difficultes}")
    public Iterable<Spot> getSpotsByDifficultes(@PathVariable("difficultes") final List<String> difficultes) {
        return spotService.getSpotsByDifficultes(difficultes);
    }

    @PutMapping("/spot/{id}")
    public Spot modifierSpot(@RequestBody Spot spot, @PathVariable("id") final Long id) {
        Optional<Spot> s = spotService.getSpot(id);

        if(s.isPresent()) {
            Spot currentSpot = s.get();

            String image = spot.getImage();
            if(image != null) {
                currentSpot.setImage(image);
            }

            String videoYoutube = spot.getVideoYoutube();
            if(videoYoutube != null) {
                currentSpot.setVideoYoutube(videoYoutube);
            }

            String nom = spot.getNom();
            if(nom != null) {
                currentSpot.setNom(nom);
            }

            String description = spot.getDescription();
            if(description != null) {
                currentSpot.setDescription(description);
            }

            Double longitude = spot.getLongitude();
            if(longitude != null) {
                currentSpot.setLongitude(longitude);
            }

            Double latitude = spot.getLatitude();
            if(latitude != null) {
                currentSpot.setLatitude(latitude);
            }

            Integer isVerifie = spot.getIsVerifie();
            if(isVerifie != null) {
                currentSpot.setIsVerifie(isVerifie);
            }

            Integer isDesactive = spot.getIsDesactive();
            if(isDesactive != null) {
                currentSpot.setIsDesactive(isDesactive);
            }

            Difficulte difficulte = spot.getDifficulte();
            if(difficulte != null) {
                currentSpot.setDifficulte(difficulte);
            }

            List<SportMecanique> sportMecaniques = spot.getSportMecaniques();
            if(sportMecaniques != null) {
                currentSpot.setSportMecaniques(sportMecaniques);
            }

            spotService.enregistrerSpot(currentSpot);
            return currentSpot;
        } else {
            return null;
        }
    }

    @DeleteMapping("/spot/{id}")
    public void supprimerSpot(@PathVariable("id") final Long id) {
        spotService.supprimerSpot(id);
    }
}
