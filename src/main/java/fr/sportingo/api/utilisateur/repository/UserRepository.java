package fr.sportingo.api.utilisateur.repository;

import fr.sportingo.api.utilisateur.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository pour les utilisateurs
 * @author Corentin Bringer
 * @version 1.0
 */
@Repository
public interface UserRepository extends CrudRepository<User, Long>
{

}
