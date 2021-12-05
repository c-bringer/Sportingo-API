package fr.sportingo.api.controller;

import fr.sportingo.api.model.MechanicalSport;
import fr.sportingo.api.service.MechanicalSportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

/**
 * RestController pour les sports mecaniques
 * @author Corentin Bringer
 * @version 1.0
 */
@RestController
public class MechanicalSportController
{
    @Autowired
    private MechanicalSportService mechanicalSportService;


    /**
     * Create - Ajoute un nouveau sport mécanique
     * @param mechanicalSport Objet MechanicalSport
     * @return Objet MechanicalSport
     */
    @PostMapping("/admin/mechanical-sport")
    public MechanicalSport saveMechanicalSport(@RequestBody MechanicalSport mechanicalSport)
    {
        return mechanicalSportService.saveMechanicalSport(mechanicalSport);
    }


    /**
     * Read - Récupère tous les sports mécaniques
     * @return Objet MechanicalSport
     */
    @GetMapping("/mechanical-sport")
    public Iterable<MechanicalSport> getMechanicalsSports()
    {
        return mechanicalSportService.getMechanicalsSports();
    }


    /**
     * Read - Récupère un sport mécanique en fonction de l'id
     * @param id Long id
     * @return MechanicalSport || null
     */
    @GetMapping("/mechanical-sport/{id}")
    public MechanicalSport getMechanicalSportById(@PathVariable("id") final Long id)
    {
        Optional<MechanicalSport> mechanicalSport = mechanicalSportService.getMechanicalSport(id);

        return mechanicalSport.orElse(null);
    }


    /**
     * Update - Mets à jour le sport mécanique en fonction de l'id
     * @param id Integer id
     * @param mechanicalSport Objet MechanicalSport
     * @return Objet MechanicalSport || null
     */
    @PutMapping("/admin/mechanical-sport/{id}")
    public MechanicalSport updateMechanicalSport(@PathVariable("id") final Long id, @RequestBody MechanicalSport mechanicalSport)
    {
        Optional<MechanicalSport> ms = mechanicalSportService.getMechanicalSport(id);

        if(ms.isPresent()) {
            MechanicalSport currentMechanicalSport = ms.get();

            String label = mechanicalSport.getLabel();
            if(label != null) {
                currentMechanicalSport.setLabel(label);
            }

            mechanicalSportService.saveMechanicalSport(currentMechanicalSport);
            return currentMechanicalSport;
        } else {
            return null;
        }
    }


    /**
     * Delete - Supprime un sport mécanique en fonction de l'id
     * @param id Integer id
     */
    @DeleteMapping("/admin/mechanical-sport/{id}")
    public void deleteMechanicalSport(@PathVariable("id") final Long id)
    {
        mechanicalSportService.deleteMechanicalSport(id);
    }
}
