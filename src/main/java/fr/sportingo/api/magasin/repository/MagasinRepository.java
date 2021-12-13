package fr.sportingo.api.magasin.repository;

import fr.sportingo.api.magasin.model.Magasin;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MagasinRepository extends CrudRepository<Magasin, Long> {

    @Query(value = "SELECT m FROM Magasin m WHERE m.utilisateur.id = ?1")
    Iterable<Magasin> getMagasinsByUtilisateur(final Long idUtilisateur);
}
