package fr.sportingo.api.utilisateur.service;

import fr.sportingo.api.utilisateur.model.UserRegistration;
import fr.sportingo.api.utilisateur.repository.UserRegistrationRepository;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Service pour les inscriptions
 * @author Corentin Bringer
 * @version 1.0
 */
@Data
@Service
public class UserRegistrationService
{
    @Autowired
    private UserRegistrationRepository userRegistrationRepository;


    /**
     * Retourne une inscription en fonction de l'id
     * @param id Integer id
     * @return Objet UserRegistration
     */
    public Optional<UserRegistration> getUserRegistration(final Long id)
    {
        return userRegistrationRepository.findById(id);
    }


    /**
     * Retourne la liste des inscriptions
     * @return Objet UserRegistration
     */
    public Iterable<UserRegistration> getUsersRegistration()
    {
        return userRegistrationRepository.findAll();
    }


    /**
     * Supprime une inscription en fonction de l'id
     * @param id Integer id
     */
    public void deleteUserRegistration(final Long id)
    {
        userRegistrationRepository.deleteById(id);
    }


    /**
     * Enregistre une nouvelle inscrption
     * @param userRegistration Objet UserRegistration
     * @return Objet UserRegistration
     */
    public UserRegistration saveUserRegistration(UserRegistration userRegistration)
    {
        return userRegistrationRepository.save(userRegistration);
    }
}
