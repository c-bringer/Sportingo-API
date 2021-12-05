package fr.sportingo.api.sportMecanique.service;

import fr.sportingo.api.sportMecanique.model.MechanicalSport;
import fr.sportingo.api.sportMecanique.repository.MechanicalSportRepository;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Service pour les sports mecaniques
 * @author Corentin Bringer
 * @version 1.0
 */
@Data
@Service
public class MechanicalSportService
{
    @Autowired
    private MechanicalSportRepository mechanicalSportRepository;


    /**
     * Récupère un sport mécanique en fonction de l'id
     * @param id Integer id
     * @return Objet MechanicalSport
     */
    public Optional<MechanicalSport> getMechanicalSport(final Long id)
    {
        return mechanicalSportRepository.findById(id);
    }


    /**
     * Récupère la liste de tous les sports mécaniques
     * @return Objet MechanicalSport
     */
    public Iterable<MechanicalSport> getMechanicalsSports()
    {
        return mechanicalSportRepository.findAll();
    }


    /**
     * Supprime un sport mécanique en fonction de l'id
     * @param id Integer id
     */
    public void deleteMechanicalSport(final Long id)
    {
        mechanicalSportRepository.deleteById(id);
    }


    /**
     * Enregistre un nouveau sport mécanique
     * @param mechanicalSport Objet MechanicalSport
     * @return Objet MechanicalSport
     */
    public MechanicalSport saveMechanicalSport(MechanicalSport mechanicalSport)
    {
        return mechanicalSportRepository.save(mechanicalSport);
    }
}
