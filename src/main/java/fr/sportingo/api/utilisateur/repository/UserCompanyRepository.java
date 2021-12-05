package fr.sportingo.api.utilisateur.repository;

import fr.sportingo.api.utilisateur.model.UserCompany;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository pour les entreprises
 * @author Corentin Bringer
 * @version 1.0
 */
@Repository
public interface UserCompanyRepository extends CrudRepository<UserCompany, Long>
{

}
