package fr.sportingo.api.motifCirculation.repository;

import fr.sportingo.api.motifCirculation.model.MotifCirculation;
import fr.sportingo.api.motifCirculation.statut.MotifCirculationStatut;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MotifCirculationRepository extends JpaRepository<MotifCirculation, Long> {
    @Query(value = "SELECT mc FROM MotifCirculation mc WHERE mc.statut = ?1")
    List<MotifCirculation> getMotifsCirculationByStatut(MotifCirculationStatut statut);
}
