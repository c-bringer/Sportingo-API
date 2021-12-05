package fr.sportingo.api.sportMecanique.repository;

import fr.sportingo.api.sportMecanique.model.MechanicalSport;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository pour les sports mecaniques
 * @author Corentin Bringer
 * @version 1.0
 */
@Repository
public interface MechanicalSportRepository extends CrudRepository<MechanicalSport, Long>
{

}
