package fr.sportingo.api.evenement.repository;

import fr.sportingo.api.evenement.model.Evenement;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository pour les evenements
 * @author Corentin Bringer
 * @version 1.0
 */
@Repository
public interface EvenementRepository extends CrudRepository<Evenement, Long>
{
    /**
     * Retourne la liste des evenements en fonction d'un utilisateur
     * @param idUtilisateur Integer idUtilisateur
     * @return Objet Evenement
     */
    @Query(value = "SELECT e FROM Evenement e WHERE e.user.id = ?1")
    Iterable<Evenement> getEvenementParUtilisateur(final Long idUtilisateur);


    /**
     * Retourne la liste des evenements en fonction d'un sport mecanique
     * @param idSportMecanique Integer idSportMecanique
     * @return Objet Evenement
     */
    @Query(value = "SELECT e FROM Evenement e WHERE e.mechanicalSport.id = ?1")
    Iterable<Evenement> getEvenementParSportMecanique(final Long idSportMecanique);
}
