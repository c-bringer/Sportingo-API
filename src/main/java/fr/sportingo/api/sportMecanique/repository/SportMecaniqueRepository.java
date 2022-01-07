package fr.sportingo.api.sportMecanique.repository;

import fr.sportingo.api.sportMecanique.model.SportMecanique;
import fr.sportingo.api.sportMecanique.status.SportMecaniqueStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SportMecaniqueRepository extends JpaRepository<SportMecanique, Long> {
    @Query(value = "SELECT sm FROM SportMecanique sm WHERE sm.status = ?1")
    List<SportMecanique> getSportsMecaniquesActives(SportMecaniqueStatus status);

    @Query(value = "SELECT sm FROM SportMecanique sm WHERE sm.status = ?1")
    List<SportMecanique> getSportsMecaniquesDesactives(SportMecaniqueStatus status);
}
