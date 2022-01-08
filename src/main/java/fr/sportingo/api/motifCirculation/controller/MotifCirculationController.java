package fr.sportingo.api.motifCirculation.controller;

import fr.sportingo.api.motifCirculation.exception.MotifCirculationNotFoundException;
import fr.sportingo.api.motifCirculation.model.MotifCirculation;
import fr.sportingo.api.motifCirculation.model.MotifCirculationModelAssembler;
import fr.sportingo.api.motifCirculation.service.MotifCirculationService;
import fr.sportingo.api.motifCirculation.status.MotifCirculationStatus;
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
public class MotifCirculationController {
    @Autowired
    private MotifCirculationService motifCirculationService;

    private final MotifCirculationModelAssembler assembler;

    MotifCirculationController(MotifCirculationModelAssembler assembler) {
        this.assembler = assembler;
    }

    @PostMapping("/private-scoped/admin/motif-circulation/ajouter")
    public ResponseEntity<?> saveMotifCirculation(@RequestBody MotifCirculation motifCirculation) {
        motifCirculation.setStatus(MotifCirculationStatus.ACTIVE);
        EntityModel<MotifCirculation> entityModel = assembler.toModel(motifCirculationService.saveMotifCirculation(motifCirculation));

        return ResponseEntity
                .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri())
                .body(entityModel);
    }

    @GetMapping("/private-scoped/admin/motif-circulation/liste-motif-circulation")
    public CollectionModel<EntityModel<MotifCirculation>> getMotifsCirculation() {
        List<EntityModel<MotifCirculation>> motifsCirculation = motifCirculationService.getMotifsCirculation().stream()
                .map(assembler::toModel)
                .collect(Collectors.toList());

        return CollectionModel.of(motifsCirculation, linkTo(methodOn(MotifCirculationController.class).getMotifsCirculation())
                .withSelfRel());
    }

    @GetMapping("/public/motif-circulation/liste-motif-circulation/active")
    public CollectionModel<EntityModel<MotifCirculation>> getMotifsCirculationActives() {
        List<EntityModel<MotifCirculation>> motifsCirculation = motifCirculationService
                .getMotifsCirculationActives(MotifCirculationStatus.ACTIVE)
                .stream()
                .map(assembler::toModel)
                .collect(Collectors.toList());

        return CollectionModel.of(motifsCirculation, linkTo(methodOn(MotifCirculationController.class).getMotifsCirculationActives())
                .withSelfRel());
    }

    @GetMapping("/private-scoped/admin/motif-circulation/liste-motif-circulation/desactive")
    public CollectionModel<EntityModel<MotifCirculation>> getMotifsCirculationDesactives() {
        List<EntityModel<MotifCirculation>> motifsCirculation = motifCirculationService
                .getMotifsCirculationDesactives(MotifCirculationStatus.DESACTIVE)
                .stream()
                .map(assembler::toModel)
                .collect(Collectors.toList());

        return CollectionModel.of(motifsCirculation, linkTo(methodOn(MotifCirculationController.class).getMotifsCirculationDesactives())
                .withSelfRel());
    }


    @GetMapping("/public/motif-circulation/{id}")
    public ResponseEntity<?> getMotifCirculation(@PathVariable("id") final Long id) {
        MotifCirculation motifCirculation = motifCirculationService.getMotifCirculation(id)
                .orElseThrow(() -> new MotifCirculationNotFoundException(id));

        if(motifCirculation.getStatus() == MotifCirculationStatus.ACTIVE) {
            return ResponseEntity.ok(assembler.toModel(motifCirculation));
        }

        return ResponseEntity.status(HttpStatus.METHOD_NOT_ALLOWED)
                .header(HttpHeaders.CONTENT_TYPE, MediaTypes.HTTP_PROBLEM_DETAILS_JSON_VALUE)
                .body(Problem.create()
                        .withTitle("Méthode non autorisée")
                        .withDetail("Vous ne pouvez pas accéder à un motif de circulation qui possède le status : "
                                + motifCirculation.getStatus()));
    }

    @PutMapping("/private-scoped/admin/motif-circulation/modifier/{id}")
    public ResponseEntity<?> updateMotifCirculation(@RequestBody MotifCirculation nouveauMotifCirculation, @PathVariable final Long id) {
        MotifCirculation updatedMotifCirculation = motifCirculationService.getMotifCirculation(id)
                .map(motifCirculation -> {
                    motifCirculation.setLibelle(nouveauMotifCirculation.getLibelle());
                    return motifCirculationService.saveMotifCirculation(motifCirculation);
                })
                .orElseGet(() -> {
                    nouveauMotifCirculation.setId(id);
                    nouveauMotifCirculation.setStatus(MotifCirculationStatus.ACTIVE);
                    return motifCirculationService.saveMotifCirculation(nouveauMotifCirculation);
                });

        EntityModel<MotifCirculation> entityModel = assembler.toModel(updatedMotifCirculation);

        return ResponseEntity
                .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri())
                .body(entityModel);
    }

    @DeleteMapping("/private-scoped/admin/motif-circulation/desactive/{id}")
    public ResponseEntity<?> desactiverMotifCirculation(@PathVariable final Long id) {
        MotifCirculation motifCirculation = motifCirculationService.getMotifCirculation(id)
                .orElseThrow(() -> new MotifCirculationNotFoundException(id));

        if(motifCirculation.getStatus() == MotifCirculationStatus.ACTIVE) {
            motifCirculation.setStatus(MotifCirculationStatus.DESACTIVE);
            return ResponseEntity.ok(assembler.toModel(motifCirculationService.saveMotifCirculation(motifCirculation)));
        }

        return ResponseEntity.status(HttpStatus.METHOD_NOT_ALLOWED)
                .header(HttpHeaders.CONTENT_TYPE, MediaTypes.HTTP_PROBLEM_DETAILS_JSON_VALUE)
                .body(Problem.create()
                        .withTitle("Méthode non autorisée")
                        .withDetail("Vous ne pouvez pas desactiver un motif de circulation qui possède le status : "
                                + motifCirculation.getStatus()));
    }
}
