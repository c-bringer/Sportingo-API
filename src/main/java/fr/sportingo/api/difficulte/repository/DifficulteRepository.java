package fr.sportingo.api.difficulte.repository;

import fr.sportingo.api.difficulte.model.Difficulte;
import fr.sportingo.api.difficulte.status.DifficulteStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DifficulteRepository extends JpaRepository<Difficulte, Long> {
    @Query(value = "SELECT d FROM Difficulte d WHERE d.status = ?1")
    List<Difficulte> getDifficultesActivees(DifficulteStatus status);

    @Query(value = "SELECT d FROM Difficulte d WHERE d.status = ?1")
    List<Difficulte> getDifficultesDesactivees(DifficulteStatus status);
}
