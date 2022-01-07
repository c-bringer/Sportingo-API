package fr.sportingo.api.sportMecanique.controller;

import fr.sportingo.api.sportMecanique.exception.SportMecaniqueNotFoundException;
import fr.sportingo.api.sportMecanique.model.SportMecaniqueModelAssembler;
import fr.sportingo.api.sportMecanique.service.SportMecaniqueService;
import fr.sportingo.api.sportMecanique.model.SportMecanique;
import fr.sportingo.api.sportMecanique.status.SportMecaniqueStatus;
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
public class SportMecaniqueController {
    @Autowired
    private SportMecaniqueService sportMecaniqueService;

    private final SportMecaniqueModelAssembler assembler;

    SportMecaniqueController(SportMecaniqueModelAssembler assembler) {
        this.assembler = assembler;
    }

    @PostMapping("/private-scoped/admin/sport-mecanique/ajouter")
    public ResponseEntity<?> saveSportMecanique(@RequestBody SportMecanique sportMecanique) {
        sportMecanique.setStatus(SportMecaniqueStatus.ACTIVE);
        EntityModel<SportMecanique> entityModel = assembler.toModel(sportMecaniqueService.saveSportMecanique(sportMecanique));

        return ResponseEntity
                .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri())
                .body(entityModel);
    }

    @GetMapping("/private-scoped/admin/sport-mecanique/liste-sport-mecanique")
    public CollectionModel<EntityModel<SportMecanique>> getSportsMecaniques() {
        List<EntityModel<SportMecanique>> sportsMecaniques = sportMecaniqueService.getSportsMecaniques().stream()
                .map(assembler::toModel)
                .collect(Collectors.toList());

        return CollectionModel.of(sportsMecaniques, linkTo(methodOn(SportMecaniqueController.class).getSportsMecaniques())
                .withSelfRel());
    }

    @GetMapping("/public/sport-mecanique/liste-sport-mecanique/active")
    public CollectionModel<EntityModel<SportMecanique>> getSportsMecaniquesActives() {
        List<EntityModel<SportMecanique>> sportsMecaniques = sportMecaniqueService
                .getSportsMecaniquesActives(SportMecaniqueStatus.ACTIVE)
                .stream()
                .map(assembler::toModel)
                .collect(Collectors.toList());

        return CollectionModel.of(sportsMecaniques, linkTo(methodOn(SportMecaniqueController.class).getSportsMecaniquesActives())
                .withSelfRel());
    }

    @GetMapping("/private-scoped/admin/sport-mecanique/liste-sport-mecanique/desactive")
    public CollectionModel<EntityModel<SportMecanique>> getSportsMecaniquesDesactives() {
        List<EntityModel<SportMecanique>> sportsMecaniques = sportMecaniqueService
                .getSportsMecaniquesActives(SportMecaniqueStatus.DESACTIVE)
                .stream()
                .map(assembler::toModel)
                .collect(Collectors.toList());

        return CollectionModel.of(sportsMecaniques, linkTo(methodOn(SportMecaniqueController.class).getSportsMecaniquesDesactives())
                .withSelfRel());
    }

    @GetMapping("/public/sport-mecanique/{id}")
    public ResponseEntity<?> getSportMecanique(@PathVariable("id") final Long id) {
        SportMecanique sportMecanique = sportMecaniqueService.getSportMecanique(id)
                .orElseThrow(() -> new SportMecaniqueNotFoundException(id));

        if(sportMecanique.getStatus() == SportMecaniqueStatus.ACTIVE) {
            return ResponseEntity.ok(assembler.toModel(sportMecanique));
        }

        return ResponseEntity.status(HttpStatus.METHOD_NOT_ALLOWED)
                .header(HttpHeaders.CONTENT_TYPE, MediaTypes.HTTP_PROBLEM_DETAILS_JSON_VALUE)
                .body(Problem.create()
                        .withTitle("Méthode non autorisée")
                        .withDetail("Vous ne pouvez pas accéder à un sport mécanique qui possède le status : "
                                + sportMecanique.getStatus()));
    }

    @PutMapping("/private-scoped/admin/sport-mecanique/modifier/{id}")
    public ResponseEntity<?> updateSportMecanique(@RequestBody SportMecanique nouveauSportMecanique, @PathVariable final Long id) {
        SportMecanique updatedSportMecanique = sportMecaniqueService.getSportMecanique(id)
                .map(sportMecanique -> {
                    sportMecanique.setLibelle(nouveauSportMecanique.getLibelle());
                    return sportMecaniqueService.saveSportMecanique(sportMecanique);
                })
                .orElseGet(() -> {
                    nouveauSportMecanique.setId(id);
                    nouveauSportMecanique.setStatus(SportMecaniqueStatus.ACTIVE);
                    return sportMecaniqueService.saveSportMecanique(nouveauSportMecanique);
                });

        EntityModel<SportMecanique> entityModel = assembler.toModel(updatedSportMecanique);

        return ResponseEntity
                .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri())
                .body(entityModel);
    }

    @DeleteMapping("/private-scoped/admin/sport-mecanique/desactiver/{id}")
    public ResponseEntity<?> desactiverSportMecanique(@PathVariable final Long id) {
        SportMecanique sportMecanique = sportMecaniqueService.getSportMecanique(id)
                .orElseThrow(() -> new SportMecaniqueNotFoundException(id));

        if(sportMecanique.getStatus() == SportMecaniqueStatus.ACTIVE) {
            sportMecanique.setStatus(SportMecaniqueStatus.DESACTIVE);
            return ResponseEntity.ok(assembler.toModel(sportMecaniqueService.saveSportMecanique(sportMecanique)));
        }

        return ResponseEntity.status(HttpStatus.METHOD_NOT_ALLOWED)
                .header(HttpHeaders.CONTENT_TYPE, MediaTypes.HTTP_PROBLEM_DETAILS_JSON_VALUE)
                .body(Problem.create()
                        .withTitle("Méthode non autorisée")
                        .withDetail("Vous ne pouvez pas desactiver un sport mécanique qui possède le status : "
                                + sportMecanique.getStatus()));
    }
}
