package fr.sportingo.api.utilisateur.model;

import fr.sportingo.api.utilisateur.controller.UtilisateurController;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class UtilisateurModelAssembler implements RepresentationModelAssembler<Utilisateur, EntityModel<Utilisateur>> {
    @Override
    public EntityModel<Utilisateur> toModel(Utilisateur utilisateur) {
        return EntityModel.of(utilisateur,
                linkTo(methodOn(UtilisateurController.class).getUtilisateur(utilisateur.getUuid())).withSelfRel(),
                linkTo(methodOn(UtilisateurController.class).getUtilisateurs()).withRel("utilisateurs"));
    }
}