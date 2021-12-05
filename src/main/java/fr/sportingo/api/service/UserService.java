package fr.sportingo.api.service;

import fr.sportingo.api.model.User;
import fr.sportingo.api.repository.UserRepository;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Service pour les utilisateurs
 * @author Corentin Bringer
 * @version 1.0
 */
@Data
@Service
public class UserService
{
    @Autowired
    private UserRepository userRepository;


    /**
     * Récupère un utilisateur en fonction de l'id
     * @param id Integer id
     * @return Objet User
     */
    public Optional<User> getUser(final Long id)
    {
        return userRepository.findById(id);
    }


    /**
     * Récupère les liste de tous les utilisateurs
     * @return Objet User
     */
    public Iterable<User> getUsers()
    {
        return userRepository.findAll();
    }


    /**
     * Supprimme un utilisateur en fonction de l'id
     * @param id Integer id
     */
    public void deleteUser(final Long id)
    {
        userRepository.deleteById(id);
    }


    /**
     * Enregistre un nouveau utilisateur
     * @param user Objet User
     * @return Objet User
     */
    public User saveUser(User user)
    {
        return userRepository.save(user);
    }
}
