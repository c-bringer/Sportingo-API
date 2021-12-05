package fr.sportingo.api.utilisateur.controller;

import fr.sportingo.api.utilisateur.model.User;
import fr.sportingo.api.utilisateur.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.Optional;

/**
 * RestController pour les utilisateurs
 * @author Corentin Bringer
 * @version 1.0
 */
@RestController
public class UserController
{
    @Autowired
    private UserService userService;


    /**
     * Create - Enregistre un nouveau utilisateur
     * @param user Objet User
     * @return Objet User
     */
    @PostMapping("/user")
    public User saveUser(@RequestBody User user)
    {
        return userService.saveUser(user);
    }


    /**
     * Read - Retourne la liste de tous les utilisateurs
     * @return Objet User
     */
    @GetMapping("/user")
    public Iterable<User> getUsers()
    {
        return userService.getUsers();
    }


    /**
     * Read - Retourne un utilisateur en fonction de l'id
     * @param id Integer id
     * @return Objet User
     */
    @GetMapping("/user/{id}")
    public User getUser(@PathVariable("id") final Long id)
    {
        Optional<User> user = userService.getUser(id);

        return user.orElse(null);
    }


    /**
     * Update - Mets à jour un utilisateur en fonction de l'id
     * @param id Integer id
     * @param user Objet User
     * @return Objet User
     */
    @PutMapping("/user/{id}")
    public User updateUser(@PathVariable("id") final Long id, @RequestBody User user)
    {
        Optional<User> u = userService.getUser(id);

        if(u.isPresent()) {
            User currentUser = u.get();

            String civility = user.getCivility();
            if(civility != null) {
                currentUser.setCivility(civility);
            }

            String firstname = user.getFirstname();
            if(firstname != null) {
                currentUser.setFirstname(firstname);
            }

            String lastname = user.getLastname();
            if(lastname != null) {
                currentUser.setLastname(lastname);
            }

            Date birthday = user.getBirthday();
            if(birthday != null) {
                currentUser.setBirthday(birthday);
            }

            String email = user.getEmail();
            if(email != null) {
                currentUser.setEmail(email);
            }

            String password = user.getPassword();
            if(password != null) {
                currentUser.setPassword(password);
            }

            Integer isAdmin = user.getIsAdmin();
            if(isAdmin != null) {
                currentUser.setIsAdmin(isAdmin);
            }

            Integer isDisabled = user.getIsDisabled();
            if(isDisabled != null) {
                currentUser.setIsDisabled(isDisabled);
            }

            userService.saveUser(currentUser);
            return currentUser;
        } else {
            return null;
        }
    }


    /**
     * Delete - Supprime un utilisateur en fonction de l'id
     * @param id Integer id
     */
    @DeleteMapping("/user/{id}")
    public void deleteUser(@PathVariable("id") final Long id)
    {
        userService.deleteUser(id);
    }
}
