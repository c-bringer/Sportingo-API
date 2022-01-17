package fr.sportingo.api.difficulte.repository;

import fr.sportingo.api.difficulte.model.Difficulte;
import fr.sportingo.api.difficulte.statut.DifficulteStatut;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DifficulteRepository extends JpaRepository<Difficulte, Long> {
    @Query(value = "SELECT d FROM Difficulte d WHERE d.statut = ?1")
    List<Difficulte> getDifficultesByStatut(DifficulteStatut statut);
}
