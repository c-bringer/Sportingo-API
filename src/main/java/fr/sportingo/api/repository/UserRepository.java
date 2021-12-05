package fr.sportingo.api.repository;

import fr.sportingo.api.model.User;
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
