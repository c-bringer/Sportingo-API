package fr.sportingo.api.magasin.service;

import fr.sportingo.api.magasin.model.Magasin;
import fr.sportingo.api.magasin.repository.MagasinRepository;
import fr.sportingo.api.magasin.statut.MagasinStatut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class MagasinService {
    @Autowired
    private MagasinRepository magasinRepository;

    public Optional<Magasin> getMagasin(final UUID uuid) {
        return magasinRepository.findById(uuid);
    }

    public List<Magasin> getMagasins() {
        return magasinRepository.findAll();
    }

    public List<Magasin> getMagasinsByUtilisateur(final UUID uuid) {
        return magasinRepository.getMagasinsByUtilisateur(uuid);
    }

    public List<Magasin> getMagasinsByStatut(MagasinStatut statut) {
        return magasinRepository.getMagasinsByStatut(statut);
    }

    public List<Magasin> getMagasinsByUtilisateurAndStatut(final UUID uuid, MagasinStatut statut) {
        return magasinRepository.getMagasinsByUtilisateurAndStatut(uuid, statut);
    }

    public Magasin saveMagasin(Magasin magasin) {
        return magasinRepository.save(magasin);
    }
}
