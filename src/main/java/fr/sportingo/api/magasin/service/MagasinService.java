package fr.sportingo.api.magasin.service;

import fr.sportingo.api.magasin.model.Magasin;
import fr.sportingo.api.magasin.repository.MagasinRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MagasinService {

    @Autowired
    private MagasinRepository magasinRepository;

    public Optional<Magasin> getMagasin(final Long id) {
        return magasinRepository.findById(id);
    }

    public Iterable<Magasin> getMagasins() {
        return magasinRepository.findAll();
    }

    public Iterable<Magasin> getMagasinsByUtilisateur(final Long idUtilisateur) {
        return magasinRepository.getMagasinsByUtilisateur(idUtilisateur);
    }

    public void supprimerMagasin(final Long id) {
        magasinRepository.deleteById(id);
    }

    public Magasin enregistrerMagasin(Magasin magasin) {
        return magasinRepository.save(magasin);
    }
}
