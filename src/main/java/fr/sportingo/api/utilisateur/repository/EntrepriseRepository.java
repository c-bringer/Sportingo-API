package fr.sportingo.api.utilisateur.repository;

import fr.sportingo.api.utilisateur.model.Entreprise;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EntrepriseRepository extends CrudRepository<Entreprise, Long> {

}
