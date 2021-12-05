package fr.sportingo.api.controller;

import fr.sportingo.api.model.Event;
import fr.sportingo.api.model.MechanicalSport;
import fr.sportingo.api.model.User;
import fr.sportingo.api.model.UserRegistration;
import fr.sportingo.api.service.UserRegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

/**
 * RestController pour les inscriptions
 * @author Corentin Bringer
 * @version 1.0
 */
@RestController
public class UserRegistrationController
{
    @Autowired
    private UserRegistrationService userRegistrationService;


    /**
     * Create - Enregistre une nouvelle inscription
     * @param userRegistration Objet UserRegistration
     * @return Objet UserRegistration
     */
    @PostMapping("/user-registration")
    public UserRegistration saveUserRegistration(@RequestBody UserRegistration userRegistration)
    {
        return userRegistrationService.saveUserRegistration(userRegistration);
    }


    /**
     * Read - Retourne la liste de toutes les incriptions
     * @return Objet UserRegistration
     */
    @GetMapping("/user-registration")
    public Iterable<UserRegistration> getUsersRegistration()
    {
        return userRegistrationService.getUsersRegistration();
    }


    /**
     * Read - Retourne une inscription en fonction de l'id
     * @param id Integer id
     * @return Objet UserRegistration
     */
    @GetMapping("/user-registration/{id}")
    public UserRegistration getUserRegistration(@PathVariable("id") final Long id)
    {
        Optional<UserRegistration> usersRegistration = userRegistrationService.getUserRegistration(id);

        return usersRegistration.orElse(null);
    }


    /**
     * Update - Mets à jour une inscription en fonction de l'id
     * @param id Integer id
     * @param userRegistration Objet UserRegistration
     * @return Objet UserRegistration
     */
    @PutMapping("/user-registration/{id}")
    public UserRegistration updateUserRegistration(@PathVariable("id") final Long id, @RequestBody UserRegistration userRegistration)
    {
        Optional<UserRegistration> ur = userRegistrationService.getUserRegistration(id);

        if(ur.isPresent()) {
            UserRegistration currentUserRegistration = ur.get();

            Integer isAccepted = userRegistration.getIsAccepted();
            if(isAccepted != null) {
                currentUserRegistration.setIsAccepted(isAccepted);
            }

            userRegistrationService.saveUserRegistration(currentUserRegistration);
            return currentUserRegistration;
        } else {
            return null;
        }
    }


    /**
     * Delete - Supprime une inscription en fonction de l'id
     * @param id Integer id
     */
    @DeleteMapping("/user-registration/{id}")
    public void deleteUserRegistration(@PathVariable("id") final Long id)
    {
        userRegistrationService.deleteUserRegistration(id);
    }
}
