package fr.sportingo.api.difficulte.controller;

import fr.sportingo.api.difficulte.exception.DifficulteNotFoundException;
import fr.sportingo.api.difficulte.model.Difficulte;
import fr.sportingo.api.difficulte.model.DifficulteModelAssembler;
import fr.sportingo.api.difficulte.service.DifficulteService;
import fr.sportingo.api.difficulte.status.DifficulteStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.hateoas.MediaTypes;
import org.springframework.hateoas.mediatype.problem.Problem;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
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
        difficulte.setStatus(DifficulteStatus.ACTIVE);
        EntityModel<Difficulte> entityModel = assembler.toModel(difficulteService.saveDifficulte(difficulte));

        return ResponseEntity
                .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri())
                .body(entityModel);
    }

    @GetMapping("/private-scoped/admin/difficulte/liste-difficulte")
    public CollectionModel<EntityModel<Difficulte>> getDifficultes() {
        List<EntityModel<Difficulte>> difficultes = difficulteService.getDifficultes().stream()
                .map(assembler::toModel)
                .collect(Collectors.toList());

        return CollectionModel.of(difficultes, linkTo(methodOn(DifficulteController.class).getDifficultes())
                .withSelfRel());
    }

    @GetMapping("/public/difficulte/liste-difficulte/active")
    public CollectionModel<EntityModel<Difficulte>> getDifficultesActivees() {
        List<EntityModel<Difficulte>> difficultes = difficulteService.getDifficultesActivees(DifficulteStatus.ACTIVE)
                .stream()
                .map(assembler::toModel)
                .collect(Collectors.toList());

        return CollectionModel.of(difficultes, linkTo(methodOn(DifficulteController.class).getDifficultes())
                .withSelfRel());
    }

    @GetMapping("/private-scoped/admin/difficulte/liste-difficulte/desactive")
    public CollectionModel<EntityModel<Difficulte>> getDifficultesDesactivees() {
        List<EntityModel<Difficulte>> difficultes = difficulteService.getDifficultesDesactivees(DifficulteStatus.DESACTIVE)
                .stream()
                .map(assembler::toModel)
                .collect(Collectors.toList());

        return CollectionModel.of(difficultes, linkTo(methodOn(DifficulteController.class).getDifficultes())
                .withSelfRel());
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

    @DeleteMapping("/private-scoped/admin/difficulte/desactiver/{id}")
    public ResponseEntity<?> desactiverDifficulte(@PathVariable final Long id) {
        Difficulte difficulte = difficulteService.getDifficulte(id)
                .orElseThrow(() -> new DifficulteNotFoundException(id));

        if(difficulte.getStatus() == DifficulteStatus.ACTIVE) {
            difficulte.setStatus(DifficulteStatus.DESACTIVE);
            return ResponseEntity.ok(assembler.toModel(difficulteService.saveDifficulte(difficulte)));
        }

        return ResponseEntity.status(HttpStatus.METHOD_NOT_ALLOWED)
                .header(HttpHeaders.CONTENT_TYPE, MediaTypes.HTTP_PROBLEM_DETAILS_JSON_VALUE)
                .body(Problem.create()
                        .withTitle("Méthode non autorisée")
                        .withDetail("Vous ne pouvez pas desactiver une difficulté qui possède le status : "
                                + difficulte.getStatus()));
    }
}
