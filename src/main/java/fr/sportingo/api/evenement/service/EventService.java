package fr.sportingo.api.evenement.service;

import fr.sportingo.api.evenement.model.Event;
import fr.sportingo.api.evenement.repository.EventRepository;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Service pour les evenements
 * @author Corentin Bringer
 * @version 1.0
 */
@Data
@Service
public class EventService
{
    @Autowired
    private EventRepository eventRepository;


    /**
     * Retourne un evenement en fonction de l'id
     * @param id Integer id
     * @return Objet Event
     */
    public Optional<Event> getEvent(final Long id)
    {
        return eventRepository.findById(id);
    }


    /**
     * Retourne la liste de tous les evenements
     * @return Objet Event
     */
    public Iterable<Event> getEvents()
    {
        return eventRepository.findAll();
    }


    /**
     * Retourne la liste des evenement publie par un utilisateur
     * @param idUser Integer idUser
     * @return Objet Event
     */
    public Iterable<Event> getEventsByUser(final Long idUser)
    {
        return eventRepository.getEventsByUser(idUser);
    }


    /**
     * Retourne la liste des evenements en fonction d'un sport mecanique
     * @param idMechanicalSport Integer idMechanicalSport
     * @return Objet Event
     */
    public Iterable<Event> getEventsByMechanicalSport(final Long idMechanicalSport)
    {
        return eventRepository.getEventsByMechanicalSport(idMechanicalSport);
    }


    /**
     * Supprime un evenement en fonction de l'id
     * @param id Integer id
     */
    public void deleteEvent(final Long id)
    {
        eventRepository.deleteById(id);
    }


    /**
     * Enregistre un nouvel evenement
     * @param event Objet Event
     * @return Objet Event
     */
    public Event saveEvent(Event event)
    {
        return eventRepository.save(event);
    }
}
