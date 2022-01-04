package fr.sportingo.api.difficulte.controller;

import fr.sportingo.api.difficulte.exception.DifficulteNotFoundException;
import fr.sportingo.api.difficulte.model.Difficulte;
import fr.sportingo.api.difficulte.model.DifficulteModelAssembler;
import fr.sportingo.api.difficulte.service.DifficulteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
public class DifficulteController {
    @Autowired
    private DifficulteService difficulteService;

    private final DifficulteModelAssembler assembler;

    DifficulteController(DifficulteModelAssembler assembler) {
        this.assembler = assembler;
    }

    @PostMapping("/private-scoped/admin/difficulte/ajouter")
    public ResponseEntity<?> saveDifficulte(@RequestBody Difficulte difficulte) {
        EntityModel<Difficulte> entityModel = assembler.toModel(difficulteService.saveDifficulte(difficulte));

        return ResponseEntity
                .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri())
                .body(entityModel);
    }

    @GetMapping("/public/difficulte/liste-difficulte")
    public CollectionModel<EntityModel<Difficulte>> getDifficultes() {
        List<EntityModel<Difficulte>> difficultes = difficulteService.getDifficultes().stream()
                .map(assembler::toModel)
                .collect(Collectors.toList());

        return CollectionModel.of(difficultes, linkTo(methodOn(DifficulteController.class).getDifficultes()).withSelfRel());
    }

    @GetMapping("/public/difficulte/{id}")
    public EntityModel<Difficulte> getDifficulte(@PathVariable("id") final Long id) {
        Difficulte difficulte = difficulteService.getDifficulte(id)
                .orElseThrow(() -> new DifficulteNotFoundException(id));

        return assembler.toModel(difficulte);
    }

    @PutMapping("/private-scoped/admin/difficulte/modifier/{id}")
    public ResponseEntity<?> updateDifficulte(@RequestBody Difficulte nouvelleDifficulte, @PathVariable final Long id) {
        Difficulte updatedDifficulte = difficulteService.getDifficulte(id)
                .map(difficulte -> {
                    difficulte.setLibelle(difficulte.getLibelle());
                    return difficulteService.saveDifficulte(nouvelleDifficulte);
                })
                .orElseGet(() -> {
                    nouvelleDifficulte.setId(id);
                    return difficulteService.saveDifficulte(nouvelleDifficulte);
                });

        EntityModel<Difficulte> entityModel = assembler.toModel(updatedDifficulte);

        return ResponseEntity
                .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri())
                .body(entityModel);
    }

    @DeleteMapping("/private-scoped/admin/difficulte/supprimer/{id}")
    public void deleteDifficulty(@PathVariable("id") final Long id) {
        difficulteService.deleteDifficulte(id);
    }
}
