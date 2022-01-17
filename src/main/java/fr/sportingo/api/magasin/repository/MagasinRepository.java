package fr.sportingo.api.magasin.repository;

import fr.sportingo.api.magasin.model.Magasin;
import fr.sportingo.api.magasin.statut.MagasinStatut;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface MagasinRepository extends JpaRepository<Magasin, UUID> {
    @Query(value = "SELECT m FROM Magasin m WHERE m.utilisateur.uuid = ?1")
    List<Magasin> getMagasinsByUtilisateur(final UUID id);

    @Query(value = "SELECT m FROM Magasin m WHERE m.statut = ?1")
    List<Magasin> getMagasinsByStatut(MagasinStatut statut);

    @Query(value = "SELECT m FROM Magasin m WHERE m.utilisateur.uuid = ?1 AND m.statut = ?2")
    List<Magasin> getMagasinsByUtilisateurAndStatut(final UUID uuid, MagasinStatut statut);
}
