package fr.sportingo.api.utilisateur.service;

import fr.sportingo.api.utilisateur.model.InscriptionUtilisateur;
import fr.sportingo.api.utilisateur.repository.InscriptionUtilisateurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

//@Service
public class InscriptionUtilisateurService {

    @Autowired
    private InscriptionUtilisateurRepository inscriptionUtilisateurRepository;

    public Optional<InscriptionUtilisateur> getInscriptionUtilisateur(final Long id) {
        return inscriptionUtilisateurRepository.findById(id);
    }

    public Iterable<InscriptionUtilisateur> getInscriptionsUtilisateurs() {
        return inscriptionUtilisateurRepository.findAll();
    }

    public void supprimerInscriptionUtilisateur(final Long id) {
        inscriptionUtilisateurRepository.deleteById(id);
    }

    public InscriptionUtilisateur enregistrerInscriptionUtilisateur(InscriptionUtilisateur inscriptionUtilisateur) {
        return inscriptionUtilisateurRepository.save(inscriptionUtilisateur);
    }
}
