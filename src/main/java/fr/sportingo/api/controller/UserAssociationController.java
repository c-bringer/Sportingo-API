package fr.sportingo.api.controller;

import fr.sportingo.api.model.UserAssociation;
import fr.sportingo.api.service.UserAssociationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.Optional;

/**
 * RestController pour les associations
 * @author Corentin Bringer
 * @version 1.0
 */
@RestController
public class UserAssociationController
{
    @Autowired
    private UserAssociationService userAssociationService;


    /**
     * Create - Enregistre une nouvelle association
     * @param userAssociation Objet UserAssociation
     * @return Objet UserAssociation
     */
    @PostMapping("/user-association")
    public UserAssociation saveUserAssociation(@RequestBody UserAssociation userAssociation)
    {
        return userAssociationService.saveUserAssociation(userAssociation);
    }


    /**
     * Read - Retourne la liste de toutes les associations
     * @return Objet UserAssociation
     */
    @GetMapping("/user-association")
    public Iterable<UserAssociation> getUsersAssociations()
    {
        return userAssociationService.getUsersAssociations();
    }

    /**
     * Read - Retourne une association en fonction de l'id
     * @param id Integer id
     * @return Objet UserAssociation
     */
    @GetMapping("/user-association/{id}")
    public UserAssociation getUserAssociation(@PathVariable("id") final Long id)
    {
        Optional<UserAssociation> usersAssociation = userAssociationService.getUserAssociation(id);

        return usersAssociation.orElse(null);
    }


    /**
     * Update - Mets à jour une association en fonction de l'id
     * @param id Integer id
     * @param userAssociation Objet UserAssociation
     * @return Objet UserAssociation
     */
    @PutMapping("/user-association/{id}")
    public UserAssociation updateUserAssociation(@PathVariable("id") final Long id, @RequestBody UserAssociation userAssociation)
    {
        Optional<UserAssociation> ua = userAssociationService.getUserAssociation(id);

        if(ua.isPresent()) {
            UserAssociation currentUserAssociation = ua.get();

            String civility = userAssociation.getCivility();
            if(civility != null) {
                currentUserAssociation.setCivility(civility);
            }

            String firstname = userAssociation.getFirstname();
            if(firstname != null) {
                currentUserAssociation.setFirstname(firstname);
            }

            String lastname = userAssociation.getLastname();
            if(lastname != null) {
                currentUserAssociation.setLastname(lastname);
            }

            Date birthday = userAssociation.getBirthday();
            if(birthday != null) {
                currentUserAssociation.setBirthday(birthday);
            }

            String email = userAssociation.getEmail();
            if(email != null) {
                currentUserAssociation.setEmail(email);
            }

            String password = userAssociation.getPassword();
            if(password != null) {
                currentUserAssociation.setPassword(password);
            }

            Integer isAdmin = userAssociation.getIsAdmin();
            if(isAdmin != null) {
                currentUserAssociation.setIsAdmin(isAdmin);
            }

            Integer isDisabled = userAssociation.getIsDisabled();
            if(isDisabled != null) {
                currentUserAssociation.setIsDisabled(isDisabled);
            }

            String label = userAssociation.getLabel();
            if(label != null) {
                currentUserAssociation.setLabel(label);
            }

            String phone = userAssociation.getPhone();
            if(phone != null) {
                currentUserAssociation.setPhone(phone);
            }

            userAssociationService.saveUserAssociation(currentUserAssociation);
            return currentUserAssociation;
        } else {
            return null;
        }
    }


    /**
     * Delete - Supprime une association en fonction de l'id
     * @param id Integer id
     */
    @DeleteMapping("/user-association/{id}")
    public void deleteUserAssociation(@PathVariable("id") final Long id)
    {
        userAssociationService.deleteUserAssociation(id);
    }
}
