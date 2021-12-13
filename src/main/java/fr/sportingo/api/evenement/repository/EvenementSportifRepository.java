package fr.sportingo.api.evenement.repository;

import fr.sportingo.api.evenement.model.EvenementSportif;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EvenementSportifRepository extends CrudRepository<EvenementSportif, Long> {

    @Query(value = "SELECT e FROM EvenementSportif e WHERE e.utilisateur.id = ?1")
    Iterable<EvenementSportif> getEvenementsSportifByUtilisateur(final Long idUtilisateur);

    @Query(value = "SELECT e FROM EvenementSportif e WHERE e.sportMecanique.id = ?1")
    Iterable<EvenementSportif> getEvenementsSportifBySportMecanique(final Long idSportMecanique);
}
