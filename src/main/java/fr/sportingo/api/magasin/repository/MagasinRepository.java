package fr.sportingo.api.magasin.repository;

import fr.sportingo.api.magasin.model.Magasin;
import fr.sportingo.api.magasin.status.MagasinStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface MagasinRepository extends JpaRepository<Magasin, UUID> {
    @Query(value = "SELECT m FROM Magasin m WHERE m.utilisateur.id = ?1")
    List<Magasin> getMagasinsByUtilisateur(final UUID id);

    @Query(value = "SELECT m FROM Magasin m WHERE m.status = ?1")
    List<Magasin> getMagasinsByStatus(MagasinStatus status);

    @Query(value = "SELECT m FROM Magasin m WHERE m.utilisateur.id = ?1 AND m.status = ?2")
    List<Magasin> getMagasinsByUtilisateurAndStatus(final UUID id, MagasinStatus status);
}
