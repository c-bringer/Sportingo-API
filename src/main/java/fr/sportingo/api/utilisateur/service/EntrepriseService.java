package fr.sportingo.api.utilisateur.service;

import fr.sportingo.api.utilisateur.model.Entreprise;
import fr.sportingo.api.utilisateur.repository.EntrepriseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class EntrepriseService {

    @Autowired
    private EntrepriseRepository entrepriseRepository;

    public Optional<Entreprise> getEntreprise(final UUID id) {
        return entrepriseRepository.findById(id);
    }

    public Iterable<Entreprise> getEntreprises() {
        return entrepriseRepository.findAll();
    }

    public void deleteEntreprise(final UUID id) {
        entrepriseRepository.deleteById(id);
    }

    public Entreprise saveEntreprise(Entreprise entreprise) {
        return entrepriseRepository.save(entreprise);
    }
}
