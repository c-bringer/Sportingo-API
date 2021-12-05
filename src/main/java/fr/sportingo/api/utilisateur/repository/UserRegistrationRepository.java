package fr.sportingo.api.utilisateur.repository;

import fr.sportingo.api.utilisateur.model.UserRegistration;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository pour les inscriptions
 * @author Corentin Bringer
 * @version 1.0
 */
@Repository
public interface UserRegistrationRepository extends CrudRepository<UserRegistration, Long>
{

}
