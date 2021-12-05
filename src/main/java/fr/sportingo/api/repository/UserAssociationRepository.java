package fr.sportingo.api.repository;

import fr.sportingo.api.model.UserAssociation;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository pour les associations
 * @author Corentin Bringer
 * @version 1.0
 */
@Repository
public interface UserAssociationRepository extends CrudRepository<UserAssociation, Long>
{

}
