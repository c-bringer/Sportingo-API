package fr.sportingo.api.evenement.service;

import fr.sportingo.api.evenement.model.Evenement;
import fr.sportingo.api.evenement.repository.EvenementRepository;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Service pour les evenements
 * @author Corentin Bringer
 * @version 1.0
 */
@Service
public class EvenementService
{
    @Autowired
    private EvenementRepository evenementRepository;


    /**
     * Retourne un evenement en fonction de l'id
     * @param id Integer id
     * @return Objet Evenement
     */
    public Optional<Evenement> getEvenement(final Long id)
    {
        return evenementRepository.findById(id);
    }


    /**
     * Retourne la liste de tous les evenements
     * @return Objet Evenement
     */
    public Iterable<Evenement> getEvenements()
    {
        return evenementRepository.findAll();
    }


    /**
     * Retourne la liste des evenement publie par un utilisateur
     * @param idUtilisateur Integer idUtilisateur
     * @return Objet Evenement
     */
    public Iterable<Evenement> getEvenementParUtilisateur(final Long idUtilisateur)
    {
        return evenementRepository.getEvenementParUtilisateur(idUtilisateur);
    }


    /**
     * Retourne la liste des evenements en fonction d'un sport mecanique
     * @param idSportMecanique Integer idSportMecanique
     * @return Objet Evenement
     */
    public Iterable<Evenement> getEvenementParSportMecanique(final Long idSportMecanique)
    {
        return evenementRepository.getEvenementParSportMecanique(idSportMecanique);
    }


    /**
     * Supprime un evenement en fonction de l'id
     * @param id Integer id
     */
    public void supprimerEvenement(final Long id)
    {
        evenementRepository.deleteById(id);
    }


    /**
     * Enregistre un nouvel evenement
     * @param evenement Objet Evenement
     * @return Objet Evenement
     */
    public Evenement enregistrerEvenement(Evenement evenement)
    {
        return evenementRepository.save(evenement);
    }
}
