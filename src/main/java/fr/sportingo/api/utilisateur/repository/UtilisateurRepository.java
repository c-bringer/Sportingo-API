package fr.sportingo.api.utilisateur.repository;

import fr.sportingo.api.utilisateur.model.Utilisateur;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UtilisateurRepository extends CrudRepository<Utilisateur, Long> {

}
