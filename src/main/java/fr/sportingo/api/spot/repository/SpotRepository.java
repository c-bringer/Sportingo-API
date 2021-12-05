package fr.sportingo.api.spot.repository;

import fr.sportingo.api.spot.model.Spot;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Repository pour les spots
 * @author Corentin Bringer
 * @version 1.0
 */
@Repository
public interface SpotRepository extends CrudRepository<Spot, Long>
{
    /**
     * Retourne la liste des spots en fonction d'un utilisateur
     * @param idUser Integer idUser
     * @return Objet Spot
     */
    @Query(value = "SELECT s FROM Spot s WHERE s.user.id = ?1")
    Iterable<Spot> getSpotsByUser(final Long idUser);


    /**
     * Retopurne la liste des spots en fonction des difficultes
     * @param difficulties List String difficulties
     * @return Objet Spot
     */
    @Query(value = "SELECT s FROM Spot s WHERE s.difficulty.label IN (?1)")
    Iterable<Spot> getSpotsByDifficulties(final List<String> difficulties);
}
