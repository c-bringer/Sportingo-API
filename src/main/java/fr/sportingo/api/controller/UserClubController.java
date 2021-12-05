package fr.sportingo.api.controller;

import fr.sportingo.api.model.UserClub;
import fr.sportingo.api.service.UserClubService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.Optional;

/**
 * RestController pour les clubs
 * @author Corentin Bringer
 * @version 1.0
 */
@RestController
public class UserClubController
{
    @Autowired
    private UserClubService userClubService;


    /**
     * Create - Enregistre un nouveau club
     * @param userClub Objet UserClub
     * @return Objet UserClub
     */
    @PostMapping("/user-club")
    public UserClub saveUserClub(@RequestBody UserClub userClub)
    {
        return userClubService.saveUserClub(userClub);
    }


    /**
     * Read - Retourne la liste de tous les clubs
     * @return Objet UserClub
     */
    @GetMapping("/user-club")
    public Iterable<UserClub> getUsersClubs()
    {
        return userClubService.getUsersClubs();
    }


    /**
     * Read - Retourne un club en fonction de l'id
     * @param id Integer id
     * @return Objet UserClub
     */
    @GetMapping("/user-club/{id}")
    public UserClub getUserClub(@PathVariable("id") final Long id)
    {
        Optional<UserClub> userClub = userClubService.getUserClub(id);

        return userClub.orElse(null);
    }


    /**
     * Update - Mets à jour un club en fonction de l'id
     * @param id Integer id
     * @param userClub Objet UserClub
     * @return Objet UserClub
     */
    @PutMapping("/user-club/{id}")
    public UserClub updateUserClub(@PathVariable("id") final Long id, @RequestBody UserClub userClub)
    {
        Optional<UserClub> uc = userClubService.getUserClub(id);

        if(uc.isPresent()) {
            UserClub currentUserClub = uc.get();

            String civility = userClub.getCivility();
            if(civility != null) {
                currentUserClub.setCivility(civility);
            }

            String firstname = userClub.getFirstname();
            if(firstname != null) {
                currentUserClub.setFirstname(firstname);
            }

            String lastname = userClub.getLastname();
            if(lastname != null) {
                currentUserClub.setLastname(lastname);
            }

            Date birthday = userClub.getBirthday();
            if(birthday != null) {
                currentUserClub.setBirthday(birthday);
            }

            String email = userClub.getEmail();
            if(email != null) {
                currentUserClub.setEmail(email);
            }

            String password = userClub.getPassword();
            if(password != null) {
                currentUserClub.setPassword(password);
            }

            Integer isAdmin = userClub.getIsAdmin();
            if(isAdmin != null) {
                currentUserClub.setIsAdmin(isAdmin);
            }

            Integer isDisabled = userClub.getIsDisabled();
            if(isDisabled != null) {
                currentUserClub.setIsDisabled(isDisabled);
            }

            String label = userClub.getLabel();
            if(label != null) {
                currentUserClub.setLabel(label);
            }

            String phone = userClub.getPhone();
            if(phone != null) {
                currentUserClub.setPhone(phone);
            }

            userClubService.saveUserClub(currentUserClub);
            return currentUserClub;
        } else {
            return null;
        }
    }


    /**
     * Delete - Supprime un club en fonction de l'id
     * @param id Integer id
     */
    @DeleteMapping("/user-club/{id}")
    public void deleteUserClub(@PathVariable("id") final Long id)
    {
        userClubService.deleteUserClub(id);
    }
}
