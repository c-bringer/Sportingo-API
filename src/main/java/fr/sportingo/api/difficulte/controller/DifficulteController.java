package fr.sportingo.api.difficulte.controller;

import fr.sportingo.api.difficulte.exception.DifficulteNotFoundException;
import fr.sportingo.api.difficulte.model.Difficulte;
import fr.sportingo.api.difficulte.model.DifficulteModelAssembler;
import fr.sportingo.api.difficulte.service.DifficulteService;
import fr.sportingo.api.difficulte.statut.DifficulteStatut;
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
        difficulte.setStatut(DifficulteStatut.ACTIVE);
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
        List<EntityModel<Difficulte>> difficultes = difficulteService.getDifficultesBystatut(DifficulteStatut.ACTIVE)
                .stream()
                .map(assembler::toModel)
                .collect(Collectors.toList());

        return CollectionModel.of(difficultes, linkTo(methodOn(DifficulteController.class).getDifficultesActivees())
                .withSelfRel());
    }

    @GetMapping("/private-scoped/admin/difficulte/liste-difficulte/desactive")
    public CollectionModel<EntityModel<Difficulte>> getDifficultesDesactivees() {
        List<EntityModel<Difficulte>> difficultes = difficulteService.getDifficultesBystatut(DifficulteStatut.DESACTIVE)
                .stream()
                .map(assembler::toModel)
                .collect(Collectors.toList());

        return CollectionModel.of(difficultes, linkTo(methodOn(DifficulteController.class).getDifficultesDesactivees())
                .withSelfRel());
    }

    @GetMapping("/public/difficulte/{id}")
    public ResponseEntity<?> getDifficulte(@PathVariable("id") final Long id) {
        Difficulte difficulte = difficulteService.getDifficulte(id)
                .orElseThrow(() -> new DifficulteNotFoundException(id));

        if(difficulte.getStatut() == DifficulteStatut.ACTIVE) {
            return ResponseEntity.ok(assembler.toModel(difficulte));
        }

        return ResponseEntity.status(HttpStatus.METHOD_NOT_ALLOWED)
                .header(HttpHeaders.CONTENT_TYPE, MediaTypes.HTTP_PROBLEM_DETAILS_JSON_VALUE)
                .body(Problem.create()
                        .withTitle("M??thode non autoris??e")
                        .withDetail("Vous ne pouvez pas acc??der ?? une difficult?? qui poss??de le statut : "
                                + difficulte.getStatut()));
    }

    @PutMapping("/private-scoped/admin/difficulte/modifier/{id}")
    public ResponseEntity<?> updateDifficulte(@RequestBody Difficulte nouvelleDifficulte, @PathVariable final Long id) {
        Difficulte updatedDifficulte = difficulteService.getDifficulte(id)
                .map(difficulte -> {
                    difficulte.setLibelle(nouvelleDifficulte.getLibelle());
                    return difficulteService.saveDifficulte(difficulte);
                })
                .orElseGet(() -> {
                    nouvelleDifficulte.setId(id);
                    nouvelleDifficulte.setStatut(DifficulteStatut.ACTIVE);
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

        if(difficulte.getStatut() == DifficulteStatut.ACTIVE) {
            difficulte.setStatut(DifficulteStatut.DESACTIVE);
            return ResponseEntity.ok(assembler.toModel(difficulteService.saveDifficulte(difficulte)));
        }

        return ResponseEntity.status(HttpStatus.METHOD_NOT_ALLOWED)
                .header(HttpHeaders.CONTENT_TYPE, MediaTypes.HTTP_PROBLEM_DETAILS_JSON_VALUE)
                .body(Problem.create()
                        .withTitle("M??thode non autoris??e")
                        .withDetail("Vous ne pouvez pas desactiver une difficult?? qui poss??de le statut : "
                                + difficulte.getStatut()));
    }
}
