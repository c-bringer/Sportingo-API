package fr.sportingo.api.motifCirculation.controller;

import fr.sportingo.api.motifCirculation.model.MotifCirculation;
import fr.sportingo.api.motifCirculation.service.MotifCirculationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping(path = "api", produces = MediaType.APPLICATION_JSON_VALUE)
public class MotifCirculationController {

    @Autowired
    private MotifCirculationService motifCirculationService;

    @PostMapping("/private-scoped/admin/motif-circulation/ajouter")
    public MotifCirculation saveMotifCirculation(@RequestBody MotifCirculation motifCirculation) {
        return motifCirculationService.saveMotifCirculation(motifCirculation);
    }

    @GetMapping("/public/motif-circulation/liste-motif-circulation")
    public Iterable<MotifCirculation> getMotifsCirculation() {
        return motifCirculationService.getMotifsCirculation();
    }

    @GetMapping("/public/motif-circulation/{id}")
    public MotifCirculation getMotifCirculation(@PathVariable("id") final Long id) {
        Optional<MotifCirculation> motifCirculation = motifCirculationService.getMotifCirculation(id);

        return motifCirculation.orElse(null);
    }

    @PutMapping("/private-scoped/admin/motif-circulation/modifier/{id}")
    public MotifCirculation updateMotifCirculation(@PathVariable("id") final Long id, @RequestBody MotifCirculation motifCirculation) {
        Optional<MotifCirculation> mc = motifCirculationService.getMotifCirculation(id);

        if(mc.isPresent()) {
            MotifCirculation currentMotifCirculation = mc.get();

            String libelle = motifCirculation.getLibelle();
            if(libelle != null) {
                currentMotifCirculation.setLibelle(libelle);
            }

            motifCirculationService.saveMotifCirculation(currentMotifCirculation);
            return currentMotifCirculation;
        } else {
            return null;
        }
    }

    @DeleteMapping("/private-scoped/admin/motif-circulation/supprimer/{id}")
    public void deleteMotifCirculation(@PathVariable("id") final Long id) {
        motifCirculationService.deleteMotifCirculation(id);
    }

}
