package fr.sportingo.api.controller;

import fr.sportingo.api.model.Category;
import fr.sportingo.api.model.Event;
import fr.sportingo.api.model.MechanicalSport;
import fr.sportingo.api.model.User;
import fr.sportingo.api.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Optional;

/**
 * RestController pour les evenements
 * @author Corentin Bringer
 * @version 1.0
 */
@RestController
public class EventController
{
    @Autowired
    private EventService eventService;


    /**
     * Create - Enregistre un nouvel evenement
     * @param event Objet Event
     * @return Objet Event
     */
    @PostMapping("/event")
    public Event saveEvent(@RequestBody Event event)
    {
        return eventService.saveEvent(event);
    }


    /**
     * Read - Retourne tous les evenements
     * @return Objet Event
     */
    @GetMapping("/event")
    public Iterable<Event> getEvents()
    {
        return eventService.getEvents();
    }


    /**
     * Read - Retourne un evenement en fonction de l'id
     * @param id Integer id
     * @return Objet Event
     */
    @GetMapping("/event/{id}")
    public Event getEvent(@PathVariable("id") final Long id)
    {
        Optional<Event> event = eventService.getEvent(id);

        return event.orElse(null);
    }


    /**
     * Read - Retourne la liste des evenements publie par un utilsateur
     * @param idUser Integer idUser
     * @return Objet Event
     */
    @GetMapping("/event/user/{idUser}")
    public Iterable<Event> getEventsByUser(@PathVariable("idUser") final Long idUser)
    {
        return eventService.getEventsByUser(idUser);
    }


    /**
     * Read - Retourne la liste des evenements en fonction d'un sport mecanique
     * @param idMechanicalSport Integer idMechanicalSport
     * @return Objet Event
     */
    @GetMapping("/event/mechanical-sport/{idMechanicalSport}")
    public Iterable<Event> getEventsByMechanicalSport(@PathVariable("idMechanicalSport") final Long idMechanicalSport)
    {
        return eventService.getEventsByMechanicalSport(idMechanicalSport);
    }


    /**
     * Update - Mets à jour un evenement en fonction de l'id
     * @param event Objet Event
     * @param id Integer id
     * @return Objet Event
     */
    @PutMapping("/event/{id}")
    public Event updateEvent(@RequestBody Event event, @PathVariable("id") final Long id)
    {
        Optional<Event> e = eventService.getEvent(id);

        if(e.isPresent()) {
            Event currentEvent = e.get();

            String image = event.getImage();
            if(image != null) {
                currentEvent.setImage(image);
            }

            String name = event.getName();
            if(name != null) {
                currentEvent.setName(name);
            }

            String description = event.getDescription();
            if(description != null) {
                currentEvent.setDescription(description);
            }

            Date dateHours = event.getDateHours();
            if(dateHours != null) {
                currentEvent.setDateHours(dateHours);
            }

            Date minimumBirthday = event.getMinimumBirthday();
            if(minimumBirthday != null) {
                currentEvent.setMinimumBirthday(minimumBirthday);
            }

            Integer nbPlaces = event.getNbPlaces();
            if(nbPlaces != null) {
                currentEvent.setNbPlaces(nbPlaces);
            }

            Double longitude = event.getLongitude();
            if(longitude != null) {
                currentEvent.setLongitude(longitude);
            }

            Double latitude = event.getLatitude();
            if(latitude != null) {
                currentEvent.setLatitude(latitude);
            }

            Integer isVerified = event.getIsVerified();
            if(isVerified != null) {
                currentEvent.setIsVerified(isVerified);
            }

            Integer isDisabled = event.getIsDisabled();
            if(isDisabled != null) {
                currentEvent.setIsDisabled(isDisabled);
            }

            MechanicalSport mechanicalSport = event.getMechanicalSport();
            if(mechanicalSport != null) {
                currentEvent.setMechanicalSport(mechanicalSport);
            }

            List<Category> categories = event.getCategories();
            if(categories != null) {
                currentEvent.setCategories(categories);
            }

            eventService.saveEvent(currentEvent);
            return currentEvent;
        } else {
            return null;
        }
    }


    /**
     * Delete - Supprime un evenement en fonction de l'id
     * @param id Integer id
     */
    @DeleteMapping("/event/{id}")
    public void deleteEvent(@PathVariable("id") final Long id)
    {
        eventService.deleteEvent(id);
    }
}
