package fr.sportingo.api.difficulte.repository;

import fr.sportingo.api.difficulte.model.Difficulte;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

//@Repository
public interface DifficulteRepository extends CrudRepository<Difficulte, Long> {

}
