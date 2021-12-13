package fr.sportingo.api.evenement.repository;

import fr.sportingo.api.evenement.model.EvenementCirculation;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EvenementCirculationRepository extends CrudRepository<EvenementCirculation, Long> {

    @Query(value = "SELECT e FROM EvenementCirculation e WHERE e.utilisateur.id = ?1")
    Iterable<EvenementCirculation> getEvenementsCirculationByUtilisateur(final Long idUtilisateur);
}
