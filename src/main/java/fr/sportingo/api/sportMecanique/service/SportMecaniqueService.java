package fr.sportingo.api.sportMecanique.service;

import fr.sportingo.api.sportMecanique.model.SportMecanique;
import fr.sportingo.api.sportMecanique.repository.SportMecaniqueRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Service pour les sports mecaniques
 * @author Corentin Bringer
 * @version 1.0
 */
@Service
public class SportMecaniqueService
{
    @Autowired
    private SportMecaniqueRepository sportMecaniqueRepository;


    /**
     * Récupère un sport mécanique en fonction de l'id
     * @param id Integer id
     * @return Objet SportMecanique
     */
    public Optional<SportMecanique> getSportMecanique(final Long id)
    {
        return sportMecaniqueRepository.findById(id);
    }


    /**
     * Récupère la liste de tous les sports mécaniques
     * @return Objet SportMecanique
     */
    public Iterable<SportMecanique> getSportsMecaniques()
    {
        return sportMecaniqueRepository.findAll();
    }


    /**
     * Supprime un sport mécanique en fonction de l'id
     * @param id Integer id
     */
    public void supprimerSportMecanique(final Long id)
    {
        sportMecaniqueRepository.deleteById(id);
    }


    /**
     * Enregistre un nouveau sport mécanique
     * @param sportMecanique Objet SportMecanique
     * @return Objet SportMecanique
     */
    public SportMecanique enregistrerSportMecanique(SportMecanique sportMecanique)
    {
        return sportMecaniqueRepository.save(sportMecanique);
    }
}
