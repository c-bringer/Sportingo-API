package fr.sportingo.api.magasin.service;

import fr.sportingo.api.magasin.model.Magasin;
import fr.sportingo.api.magasin.repository.MagasinRepository;
import fr.sportingo.api.magasin.status.MagasinStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class MagasinService {
    @Autowired
    private MagasinRepository magasinRepository;

    public Optional<Magasin> getMagasin(final UUID id) {
        return magasinRepository.findById(id);
    }

    public List<Magasin> getMagasins() {
        return magasinRepository.findAll();
    }

    public List<Magasin> getMagasinsByUtilisateur(final UUID id) {
        return magasinRepository.getMagasinsByUtilisateur(id);
    }

    public List<Magasin> getMagasinsByStatus(MagasinStatus status) {
        return magasinRepository.getMagasinsByStatus(status);
    }

    public List<Magasin> getMagasinsByUtilisateurAndStatus(final UUID id, MagasinStatus status) {
        return magasinRepository.getMagasinsByUtilisateurAndStatus(id, status);
    }

    public Magasin saveMagasin(Magasin magasin) {
        return magasinRepository.save(magasin);
    }
}
