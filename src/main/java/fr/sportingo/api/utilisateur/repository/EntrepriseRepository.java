package fr.sportingo.api.utilisateur.repository;

import fr.sportingo.api.utilisateur.model.Entreprise;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface EntrepriseRepository extends CrudRepository<Entreprise, UUID> {

}
