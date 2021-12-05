package fr.sportingo.api.repository;

import fr.sportingo.api.model.Category;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository pour les categories
 * @author Corentin Bringer
 * @version 1.0
 */
@Repository
public interface CategoryRepository extends CrudRepository<Category, Long>
{

}
