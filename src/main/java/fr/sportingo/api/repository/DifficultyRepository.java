package fr.sportingo.api.repository;

import fr.sportingo.api.model.Difficulty;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository pour les difficultes
 * @author Corentin Bringer
 * @version 1.0
 */
@Repository
public interface DifficultyRepository extends CrudRepository<Difficulty, Long>
{

}
