package fr.sportingo.api.sportMecanique.repository;

import fr.sportingo.api.sportMecanique.model.SportMecanique;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SportMecaniqueRepository extends CrudRepository<SportMecanique, Long> {

}
