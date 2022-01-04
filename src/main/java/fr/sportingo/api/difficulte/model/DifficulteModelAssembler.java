package fr.sportingo.api.difficulte.model;

import fr.sportingo.api.difficulte.controller.DifficulteController;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class DifficulteModelAssembler implements RepresentationModelAssembler<Difficulte, EntityModel<Difficulte>> {
    @Override
    public EntityModel<Difficulte> toModel(Difficulte difficulte) {
        return EntityModel.of(difficulte,
                linkTo(methodOn(DifficulteController.class).getDifficulte(difficulte.getId())).withSelfRel(),
                linkTo(methodOn(DifficulteController.class).getDifficultes()).withRel("difficultes"));
    }
}
