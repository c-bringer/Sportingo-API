package fr.sportingo.api.evenement.repository;

import fr.sportingo.api.evenement.model.Evenement;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EvenementRepository extends CrudRepository<Evenement, Long> {

    @Query(value = "SELECT e FROM Evenement e WHERE e.utilisateur.id = ?1")
    Iterable<Evenement> getEvenementsByUtilisateur(final Long idUtilisateur);
}
