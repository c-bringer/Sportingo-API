package fr.sportingo.api.repository;

import fr.sportingo.api.model.UserClub;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository pour les clubs
 * @author Corentin Bringer
 * @version 1.0
 */
@Repository
public interface UserClubRepository extends CrudRepository<UserClub, Long>
{

}
