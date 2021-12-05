package fr.sportingo.api.difficulte.service;

import fr.sportingo.api.difficulte.model.Difficulte;
import fr.sportingo.api.difficulte.repository.DifficulteRepository;
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
public class DifficulteService
{
    @Autowired
    private DifficulteRepository difficulteRepository;


    /**
     * Retourne une difficulté
     * @param id Integer id
     * @return Objet Difficulte
     */
    public Optional<Difficulte> getDifficulte(final Long id)
    {
        return difficulteRepository.findById(id);
    }


    /**
     * Retourne la liste de toutes les difficultés
     * @return Objet Difficulte
     */
    public Iterable<Difficulte> getDifficultes()
    {
        return difficulteRepository.findAll();
    }


    /**
     * Suppprime une difficulté en fonction de l'id
     * @param id Integer id
     */
    public void supprimmerDifficulte(final Long id)
    {
        difficulteRepository.deleteById(id);
    }


    /**
     * Enregistre une difficulté
     * @param difficulte Objet Difficulte
     * @return Objet Difficulte
     */
    public Difficulte enregistreDifficulte(Difficulte difficulte)
    {
        return difficulteRepository.save(difficulte);
    }
}
