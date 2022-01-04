package fr.sportingo.api.difficulte.repository;

import fr.sportingo.api.difficulte.model.Difficulte;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DifficulteRepository extends JpaRepository<Difficulte, Long> {

}
