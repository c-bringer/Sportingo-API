package fr.sportingo.api.magasin.controller;

import fr.sportingo.api.magasin.exception.MagasinNotFoundException;
import fr.sportingo.api.magasin.model.Magasin;
import fr.sportingo.api.magasin.model.MagasinModelAssembler;
import fr.sportingo.api.magasin.service.MagasinService;
import fr.sportingo.api.magasin.statut.MagasinStatut;
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
import java.util.UUID;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
public class MagasinController {
    @Autowired
    private MagasinService magasinService;

    private final MagasinModelAssembler assembler;

    MagasinController(MagasinModelAssembler assembler) {
        this.assembler = assembler;
    }

    @PostMapping("/private/magasin/ajouter")
    public ResponseEntity<?> saveMagasin(@RequestBody Magasin magasin) {
        magasin.setStatut(MagasinStatut.EN_ATTENTE);
        EntityModel<Magasin> entityModel = assembler.toModel(magasinService.saveMagasin(magasin));

        return ResponseEntity
                .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri())
                .body(entityModel);
    }

    @GetMapping("/private-scoped/admin/magasin/liste-magasin")
    public CollectionModel<EntityModel<Magasin>> getMagasins() {
        List<EntityModel<Magasin>> magasins = magasinService.getMagasins().stream()
                .map(assembler::toModel)
                .collect(Collectors.toList());

        return CollectionModel.of(magasins, linkTo(methodOn(MagasinController.class).getMagasins())
                .withSelfRel());
    }

    @GetMapping("/private/magasin/liste-magasin/utilisateur/{uuid}")
    public CollectionModel<EntityModel<Magasin>> getMagasinsByUtilisateur(@PathVariable("uuid") final UUID uuid) {
        List<EntityModel<Magasin>> magasins = magasinService.getMagasinsByUtilisateur(uuid).stream()
                .map(assembler::toModel)
                .collect(Collectors.toList());

        return CollectionModel.of(magasins, linkTo(methodOn(MagasinController.class).getMagasinsByUtilisateur(uuid))
                .withSelfRel());
    }

    @GetMapping("/private/magasin/liste-magasin/{statut}/utilisateur/{uuid}")
    public CollectionModel<EntityModel<Magasin>> getMagasinsByUtilisateurAndStatut(
            @PathVariable("statut") String statut,
            @PathVariable("uuid") final UUID uuid) {
        MagasinStatut statutSelected = null;

        switch(statut) {
            case "active":
                statutSelected = MagasinStatut.ACTIVE;
                break;
            case "desactive":
                statutSelected = MagasinStatut.DESACTIVE;
                break;
            case "refuse":
                statutSelected = MagasinStatut.REFUSE;
                break;
            case "en-atttente":
                statutSelected = MagasinStatut.EN_ATTENTE;
                break;
            default:
        }

        List<EntityModel<Magasin>> magasins = magasinService.getMagasinsByUtilisateurAndStatut(uuid, statutSelected)
                .stream()
                .map(assembler::toModel)
                .collect(Collectors.toList());

        return CollectionModel.of(magasins, linkTo(methodOn(MagasinController.class).getMagasinsByUtilisateurAndStatut(statut, uuid))
                .withSelfRel());
    }

    @GetMapping("/public/magasin/liste-magasin/active")
    public CollectionModel<EntityModel<Magasin>> getMagasinsActives() {
        List<EntityModel<Magasin>> magasins = magasinService.getMagasinsByStatut(MagasinStatut.ACTIVE)
                .stream()
                .map(assembler::toModel)
                .collect(Collectors.toList());

        return CollectionModel.of(magasins, linkTo(methodOn(MagasinController.class).getMagasinsActives())
                .withSelfRel());
    }

    @GetMapping("/private-scoped/admin/magasin/liste-magasin/{statut}")
    public CollectionModel<EntityModel<Magasin>> getMagasinsByStatut(@PathVariable("statut") String statut) {
        MagasinStatut statutSelected = null;

        switch(statut) {
            case "active":
                statutSelected = MagasinStatut.ACTIVE;
                break;
            case "desactive":
                statutSelected = MagasinStatut.DESACTIVE;
                break;
            case "refuse":
                statutSelected = MagasinStatut.REFUSE;
                break;
            case "en-atttente":
                statutSelected = MagasinStatut.EN_ATTENTE;
                break;
            default:
        }

        List<EntityModel<Magasin>> magasins = magasinService.getMagasinsByStatut(statutSelected)
                .stream()
                .map(assembler::toModel)
                .collect(Collectors.toList());

        return CollectionModel.of(magasins, linkTo(methodOn(MagasinController.class).getMagasinsByStatut(statut))
                .withSelfRel());
    }

    @GetMapping("/public/magasin/{uuid}")
    public ResponseEntity<?> getMagasin(@PathVariable("uuid") final UUID uuid) {
        Magasin magasin = magasinService.getMagasin(uuid)
                .orElseThrow(() -> new MagasinNotFoundException(uuid));

        if(magasin.getStatut() == MagasinStatut.ACTIVE) {
            return ResponseEntity.ok(assembler.toModel(magasin));
        }

        return ResponseEntity.status(HttpStatus.METHOD_NOT_ALLOWED)
                .header(HttpHeaders.CONTENT_TYPE, MediaTypes.HTTP_PROBLEM_DETAILS_JSON_VALUE)
                .body(Problem.create()
                        .withTitle("Méthode non autorisée")
                        .withDetail("Vous ne pouvez pas accéder à un magasin qui possède le statut : "
                                + magasin.getStatut()));
    }

    @PutMapping("/private/magasin/modifier/{uuid}")
    public ResponseEntity<?> updateMagasin(@RequestBody Magasin nouveauMagasin, @PathVariable final UUID uuid) {
        Magasin updatedMagasin = magasinService.getMagasin(uuid)
                .map(magasin -> {
                    magasin.setNom(nouveauMagasin.getNom());
                    magasin.setAdresse(nouveauMagasin.getAdresse());
                    magasin.setCodePostal(nouveauMagasin.getCodePostal());
                    magasin.setVille(nouveauMagasin.getVille());
                    magasin.setTelephone(nouveauMagasin.getTelephone());
                    magasin.setEmail(nouveauMagasin.getEmail());
                    return magasinService.saveMagasin(magasin);
                })
                .orElseGet(() -> {
                    nouveauMagasin.setUuid(uuid);
                    nouveauMagasin.setStatut(MagasinStatut.EN_ATTENTE);
                    return magasinService.saveMagasin(nouveauMagasin);
                });

        EntityModel<Magasin> entityModel = assembler.toModel(updatedMagasin);

        return ResponseEntity
                .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri())
                .body(entityModel);
    }

    @DeleteMapping("/private/magasin/desactiver/{uuid}")
    public ResponseEntity<?> desactiverMagasin(@PathVariable final UUID uuid) {
        Magasin magasin = magasinService.getMagasin(uuid)
                .orElseThrow(() -> new MagasinNotFoundException(uuid));

        if(magasin.getStatut() == MagasinStatut.ACTIVE) {
            magasin.setStatut(MagasinStatut.DESACTIVE);
            return ResponseEntity.ok(assembler.toModel(magasinService.saveMagasin(magasin)));
        }

        return ResponseEntity.status(HttpStatus.METHOD_NOT_ALLOWED)
                .header(HttpHeaders.CONTENT_TYPE, MediaTypes.HTTP_PROBLEM_DETAILS_JSON_VALUE)
                .body(Problem.create()
                        .withTitle("Méthode non autorisée")
                        .withDetail("Vous ne pouvez pas desactiver un magasin qui possède le statut : "
                                + magasin.getStatut()));
    }
}
