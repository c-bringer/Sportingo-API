package fr.sportingo.api.utilisateur.repository;

import fr.sportingo.api.utilisateur.model.InscriptionUtilisateur;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

//@Repository
public interface InscriptionUtilisateurRepository extends CrudRepository<InscriptionUtilisateur, Long> {

}
