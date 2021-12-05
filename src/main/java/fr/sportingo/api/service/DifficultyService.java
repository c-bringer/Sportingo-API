package fr.sportingo.api.service;

import fr.sportingo.api.model.Difficulty;
import fr.sportingo.api.repository.DifficultyRepository;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Service pour les difficultes
 * @author Corentin Bringer
 * @version 1.0
 */
@Data
@Service
public class DifficultyService
{
    @Autowired
    private DifficultyRepository difficultyRepository;


    /**
     * Retourne une difficulté
     * @param id Integer id
     * @return Objet Difficulty
     */
    public Optional<Difficulty> getDifficulty(final Long id)
    {
        return difficultyRepository.findById(id);
    }


    /**
     * Retourne la liste de toutes les difficultés
     * @return Objet Difficulty
     */
    public Iterable<Difficulty> getDifficulties()
    {
        return difficultyRepository.findAll();
    }


    /**
     * Suppprime une difficulté en fonction de l'id
     * @param id Integer id
     */
    public void deleteDifficulty(final Long id)
    {
        difficultyRepository.deleteById(id);
    }


    /**
     * Enregistre une difficulté
     * @param difficulty Objet Difficulty
     * @return Objet Difficulty
     */
    public Difficulty saveDifficulty(Difficulty difficulty)
    {
        return difficultyRepository.save(difficulty);
    }
}
