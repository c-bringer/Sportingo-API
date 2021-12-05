package fr.sportingo.api.controller;

import fr.sportingo.api.model.Difficulty;
import fr.sportingo.api.model.MechanicalSport;
import fr.sportingo.api.model.Spot;
import fr.sportingo.api.service.SpotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 * RestController pour les spots
 * @author Corentin Bringer
 * @version 1.0
 */
@RestController
public class SpotController
{
    @Autowired
    private SpotService spotService;


    /**
     * Create - Enregistre un nouveau spot
     * @param spot Objet Spot
     * @return Objet Spot
     */
    @PostMapping("/spot")
    public Spot saveSpot(@RequestBody Spot spot)
    {
        return spotService.saveSpot(spot);
    }


    /**
     * Read - Retourne un spot en fonction de l'id
     * @param id Integer id
     * @return Objet Spot
     */
    @GetMapping("/spot/{id}")
    public Spot getSpot(@PathVariable("id") final Long id)
    {
        Optional<Spot> spot = spotService.getSpot(id);

        return spot.orElse(null);
    }


    /**
     * Read - Retourne la liste de tous les spots
     * @return Objet Spot
     */
    @GetMapping("/spot")
    public Iterable<Spot> getSpots()
    {
        return spotService.getSpots();
    }


    /**
     * Read - Retourne la liste des spots publie par un utilisateur
     * @param idUser Integer idUser
     * @return Objet User
     */
    @GetMapping("/spot/user/{idUser}")
    public Iterable<Spot> getSpotsByUser(@PathVariable("idUser") final Long idUser)
    {
        return spotService.getSpotsByUser(idUser);
    }


    /**
     * Read - Retourne la liste des spots en fonction des difficules
     * @param difficulties List difficulties
     * @return Objet Spot
     */
    @GetMapping("/spot/difficulty/{difficulties}")
    public Iterable<Spot> getSpotsByDifficulties(@PathVariable("difficulties") final List<String> difficulties)
    {
        return spotService.getSpotsByDifficulties(difficulties);
    }


    /**
     * Update - Mets à jour un spot en fonction de l'id
     * @param spot Objet Spot
     * @param id Integer id
     * @return Objet Spot
     */
    @PutMapping("/spot/{id}")
    public Spot updateSpot(@RequestBody Spot spot, @PathVariable("id") final Long id)
    {
        Optional<Spot> s = spotService.getSpot(id);

        if(s.isPresent()) {
            Spot currentSpot = s.get();

            String image = spot.getImage();
            if(image != null) {
                currentSpot.setImage(image);
            }

            String youtubeLink = spot.getYoutubeLink();
            if(youtubeLink != null) {
                currentSpot.setYoutubeLink(youtubeLink);
            }

            String name = spot.getName();
            if(name != null) {
                currentSpot.setName(name);
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

            Integer isVerified = spot.getIsVerified();
            if(isVerified != null) {
                currentSpot.setIsVerified(isVerified);
            }

            Integer isDisabled = spot.getIsDisabled();
            if(isDisabled != null) {
                currentSpot.setIsDisabled(isDisabled);
            }

            Difficulty difficulty = spot.getDifficulty();
            if(difficulty != null) {
                currentSpot.setDifficulty(difficulty);
            }

            List<MechanicalSport> mechanicalSports = spot.getMechanicalSports();
            if(mechanicalSports != null) {
                currentSpot.setMechanicalSports(mechanicalSports);
            }

            spotService.saveSpot(currentSpot);
            return currentSpot;
        } else {
            return null;
        }
    }


    /**
     * Delete - Supprime un spot en fonction de l'id
     * @param id Integer id
     */
    @DeleteMapping("/spot/{id}")
    public void deleteSpot(@PathVariable("id") final Long id)
    {
        spotService.deleteSpot(id);
    }
}
