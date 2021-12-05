package fr.sportingo.api.repository;

import fr.sportingo.api.model.MechanicalSport;
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
