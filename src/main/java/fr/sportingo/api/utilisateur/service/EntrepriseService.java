package fr.sportingo.api.utilisateur.service;

import fr.sportingo.api.utilisateur.model.Entreprise;
import fr.sportingo.api.utilisateur.repository.EntrepriseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class EntrepriseService {

    @Autowired
    private EntrepriseRepository entrepriseRepository;

    public Optional<Entreprise> getEntreprise(final Long id) {
        return entrepriseRepository.findById(id);
    }

    public Iterable<Entreprise> getEntreprises() {
        return entrepriseRepository.findAll();
    }

    public void supprimerEntreprise(final Long id) {
        entrepriseRepository.deleteById(id);
    }

    public Entreprise enregistrerEntreprise(Entreprise entreprise) {
        return entrepriseRepository.save(entreprise);
    }
}
