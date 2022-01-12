package fr.sportingo.api.magasin.controller;

import fr.sportingo.api.difficulte.controller.DifficulteController;
import fr.sportingo.api.difficulte.model.Difficulte;
import fr.sportingo.api.difficulte.model.DifficulteModelAssembler;
import fr.sportingo.api.difficulte.status.DifficulteStatus;
import fr.sportingo.api.magasin.model.Magasin;
import fr.sportingo.api.magasin.model.MagasinModelAssembler;
import fr.sportingo.api.magasin.service.MagasinService;
import fr.sportingo.api.magasin.status.MagasinStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
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
        magasin.setStatus(MagasinStatus.EN_ATTENTE);
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

    @GetMapping("/public/magasin/liste-magasin/active")
    public CollectionModel<EntityModel<Magasin>> getMagasinsActives() {
        List<EntityModel<Magasin>> magasins = magasinService.getMagasinsByStatus(MagasinStatus.ACTIVE)
                .stream()
                .map(assembler::toModel)
                .collect(Collectors.toList());

        return CollectionModel.of(magasins, linkTo(methodOn(MagasinController.class).getMagasinsActives())
                .withSelfRel());
    }

    @GetMapping("/private-scoped/admin/magasin/liste-magasin/desactive")
    public CollectionModel<EntityModel<Magasin>> getMagasinsDesactives() {
        List<EntityModel<Magasin>> magasins = magasinService.getMagasinsByStatus(MagasinStatus.DESACTIVE)
                .stream()
                .map(assembler::toModel)
                .collect(Collectors.toList());

        return CollectionModel.of(magasins, linkTo(methodOn(MagasinController.class).getMagasinsDesactives())
                .withSelfRel());
    }

    @GetMapping("/private-scoped/admin/magasin/liste-magasin/en-attente")
    public CollectionModel<EntityModel<Magasin>> getMagasinsEnAttente() {
        List<EntityModel<Magasin>> magasins = magasinService.getMagasinsByStatus(MagasinStatus.EN_ATTENTE)
                .stream()
                .map(assembler::toModel)
                .collect(Collectors.toList());

        return CollectionModel.of(magasins, linkTo(methodOn(MagasinController.class).getMagasinsEnAttente())
                .withSelfRel());
    }

    @GetMapping("/private-scoped/admin/magasin/liste-magasin/refuse")
    public CollectionModel<EntityModel<Magasin>> getMagasinsEnAttente() {
        List<EntityModel<Magasin>> magasins = magasinService.getMagasinsByStatus(MagasinStatus.REFUSE)
                .stream()
                .map(assembler::toModel)
                .collect(Collectors.toList());

        return CollectionModel.of(magasins, linkTo(methodOn(MagasinController.class).getMagasinsEnAttente())
                .withSelfRel());
    }












    @GetMapping("/public/magasin/{id}")
    public Magasin getMagasin(@PathVariable("id") final Long id) {
        Optional<Magasin> magasin = magasinService.getMagasin(id);

        return magasin.orElse(null);
    }

    @GetMapping("/public/magasin/utilisateur/{idUser}")
    public Iterable<Magasin> getMagasinsByUtilisateur(@PathVariable("idUser") final Long idUtilisateur) {
        return magasinService.getMagasinsByUtilisateur(idUtilisateur);
    }

    @PutMapping("/private/magasin/modifier/{id}")
    public Magasin updateMagasin(@RequestBody Magasin magasin, @PathVariable("id") final Long id) {
        Optional<Magasin> m = magasinService.getMagasin(id);

        if(m.isPresent()) {
            Magasin currentMagasin = m.get();

            String nom = magasin.getNom();
            if(nom != null) {
                currentMagasin.setNom(nom);
            }

            String adresse = magasin.getAdresse();
            if(adresse != null) {
                currentMagasin.setAdresse(adresse);
            }

            String codePostal = magasin.getCodePostal();
            if(codePostal != null) {
                currentMagasin.setCodePostal(codePostal);
            }

            String ville = magasin.getVille();
            if(ville != null) {
                currentMagasin.setVille(ville);
            }

            magasinService.saveMagasin(currentMagasin);
            return currentMagasin;
        } else {
            return null;
        }
    }

    @DeleteMapping("/private/magasin/supprimer/{id}")
    public void deleteMagasin(@PathVariable("id") final Long id) {
        magasinService.deleteMagasin(id);
    }
}
