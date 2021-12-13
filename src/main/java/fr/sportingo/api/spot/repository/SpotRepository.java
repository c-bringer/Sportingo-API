package fr.sportingo.api.spot.repository;

import fr.sportingo.api.spot.model.Spot;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SpotRepository extends CrudRepository<Spot, Long> {

    @Query(value = "SELECT s FROM Spot s WHERE s.utilisateur.id = ?1")
    Iterable<Spot> getSpotsByUtilisateur(final Long idUtilisateur);

    @Query(value = "SELECT s FROM Spot s WHERE s.difficulte.libelle IN (?1)")
    Iterable<Spot> getSpotsByDifficultes(final List<String> difficultes);
}
