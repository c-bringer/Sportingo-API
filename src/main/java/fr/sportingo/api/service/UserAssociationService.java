package fr.sportingo.api.service;

import fr.sportingo.api.model.UserAssociation;
import fr.sportingo.api.repository.UserAssociationRepository;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Service pour les associations
 * @author Corentin Bringer
 * @version 1.0
 */
@Data
@Service
public class UserAssociationService
{
    @Autowired
    private UserAssociationRepository userAssociationRepository;


    /**
     * Retourne une association en fonction de l'id
     * @param id Integer id
     * @return Objet UserAssociation
     */
    public Optional<UserAssociation> getUserAssociation(final Long id)
    {
        return userAssociationRepository.findById(id);
    }


    /**
     * Retourne la liste de toutes les associations
     * @return Objet UserAssociation
     */
    public Iterable<UserAssociation> getUsersAssociations()
    {
        return userAssociationRepository.findAll();
    }


    /**
     * Supprime une association en fonction de l'id
     * @param id Integer id
     */
    public void deleteUserAssociation(final Long id)
    {
        userAssociationRepository.deleteById(id);
    }


    /**
     * Enregistre une nouvelle association
     * @param userAssociation Objet UserAssociation
     * @return Objet UserAssociation
     */
    public UserAssociation saveUserAssociation(UserAssociation userAssociation)
    {
        return userAssociationRepository.save(userAssociation);
    }
}
