package fr.sportingo.api.utilisateur.service;

import fr.sportingo.api.utilisateur.model.InscriptionUtilisateur;
import fr.sportingo.api.utilisateur.repository.InscriptionUtilisateurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class InscriptionUtilisateurService {

    @Autowired
    private InscriptionUtilisateurRepository inscriptionRepository;

    public Optional<InscriptionUtilisateur> getInscriptionUtilisateur(final Long id) {
        return inscriptionRepository.findById(id);
    }

    public Iterable<InscriptionUtilisateur> getInscriptionsUtilisateurs() {
        return inscriptionRepository.findAll();
    }

    public void deleteInscriptionUtilisateur(final Long id) {
        inscriptionRepository.deleteById(id);
    }

    public InscriptionUtilisateur saveInscriptionUtilisateur(InscriptionUtilisateur inscription) {
        return inscriptionRepository.save(inscription);
    }
}
