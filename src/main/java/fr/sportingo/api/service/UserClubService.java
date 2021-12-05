package fr.sportingo.api.service;

import fr.sportingo.api.model.UserClub;
import fr.sportingo.api.repository.UserClubRepository;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Service pour les clubs
 * @author Corentin Bringer
 * @version 1.0
 */
@Data
@Service
public class UserClubService
{
    @Autowired
    private UserClubRepository userClubRepository;


    /**
     * Retourne un club en fonction de l'id
     * @param id Integer id
     * @return Objet UserClub
     */
    public Optional<UserClub> getUserClub(final Long id)
    {
        return userClubRepository.findById(id);
    }


    /**
     * Retourne la liste de tous les clubs
     * @return Objet UserClub
     */
    public Iterable<UserClub> getUsersClubs()
    {
        return userClubRepository.findAll();
    }


    /**
     * Supprime un club en fonction de l'id
     * @param id Integer id
     */
    public void deleteUserClub(final Long id)
    {
        userClubRepository.deleteById(id);
    }


    /**
     * Enregistre un nouveau club
     * @param userClub Objet UserClub
     * @return Objet UsersClub
     */
    public UserClub saveUserClub(UserClub userClub)
    {
        return userClubRepository.save(userClub);
    }
}
