package fr.sportingo.api.spot.service;

import fr.sportingo.api.spot.repository.SpotRepository;
import fr.sportingo.api.spot.model.Spot;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Getter
@Setter
@Service
public class SpotService {

    @Autowired
    private SpotRepository spotRepository;

    public Optional<Spot> getSpot(final Long id) {
        return spotRepository.findById(id);
    }

    public Iterable<Spot> getSpots() {
        return spotRepository.findAll();
    }

    public Iterable<Spot> getSpotsByUtilisateur(final Long idUtilisateur) {
        return spotRepository.getSpotsByUtilisateur(idUtilisateur);
    }

    public Iterable<Spot> getSpotsByDifficultes(final List<String> difficultes) {
        return spotRepository.getSpotsByDifficultes(difficultes);
    }

    public void deleteSpot(final Long id) {
        spotRepository.deleteById(id);
    }

    public Spot saveSpot(Spot spot) {
        return spotRepository.save(spot);
    }
}
