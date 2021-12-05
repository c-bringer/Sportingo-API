package fr.sportingo.api.spot.service;

import fr.sportingo.api.spot.repository.SpotRepository;
import fr.sportingo.api.spot.model.Spot;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Service pour les spots
 * @author Corentin Bringer
 * @version 1.0
 */
@Data
@Service
public class SpotService
{
    @Autowired
    private SpotRepository spotRepository;


    /**
     * Retourne un spot en fonction de l'id
     * @param id Integer id
     * @return Objet Spot
     */
    public Optional<Spot> getSpot(final Long id)
    {
        return spotRepository.findById(id);
    }


    /**
     * Retourne la liste des spots
     * @return Objet Spot
     */
    public Iterable<Spot> getSpots()
    {
        return spotRepository.findAll();
    }


    /**
     * Retourne la liste des spots publie par un utilisateur
     * @param idUser Integer idUser
     * @return Objet Spot
     */
    public Iterable<Spot> getSpotsByUser(final Long idUser)
    {
        return spotRepository.getSpotsByUser(idUser);
    }


    /**
     * Retourne la liste des spots en fonction des difficultes
     * @param difficulties List String difficulties
     * @return Objet Spot
     */
    public Iterable<Spot> getSpotsByDifficulties(final List<String> difficulties)
    {
        return spotRepository.getSpotsByDifficulties(difficulties);
    }


    /**
     * Supprime un spot en fonction de l'id
     * @param id Integer id
     */
    public void deleteSpot(final Long id)
    {
        spotRepository.deleteById(id);
    }


    /**
     * Enregistre un nouveau spot
     * @param spot Objet Spot
     * @return Objet Spot
     */
    public Spot saveSpot(Spot spot)
    {
        return spotRepository.save(spot);
    }
}
