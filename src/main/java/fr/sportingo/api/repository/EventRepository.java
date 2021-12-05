package fr.sportingo.api.repository;

import fr.sportingo.api.model.Event;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * Repository pour les evenements
 * @author Corentin Bringer
 * @version 1.0
 */
@Repository
public interface EventRepository extends CrudRepository<Event, Long>
{
    /**
     * Retourne la liste des evenements en fonction d'un utilisateur
     * @param idUser Integer idUser
     * @return Objet Event
     */
    @Query(value = "SELECT e FROM Event e WHERE e.user.id = ?1")
    Iterable<Event> getEventsByUser(final Long idUser);


    /**
     * Retourne la liste des evenements en fonction d'un sport mecanique
     * @param idMechanicalUser Integer idMechanicalSport
     * @return Objet Event
     */
    @Query(value = "SELECT e FROM Event e WHERE e.mechanicalSport.id = ?1")
    Iterable<Event> getEventsByMechanicalSport(final Long idMechanicalUser);
}
