package fr.sportingo.api.sportMecanique.repository;

import fr.sportingo.api.sportMecanique.model.SportMecanique;
import fr.sportingo.api.sportMecanique.statut.SportMecaniqueStatut;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SportMecaniqueRepository extends JpaRepository<SportMecanique, Long> {
    @Query(value = "SELECT sm FROM SportMecanique sm WHERE sm.statut = ?1")
    List<SportMecanique> getSportMecaniquesByStatut(SportMecaniqueStatut statut);
}
