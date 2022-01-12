package fr.sportingo.api.magasin.model;

import fr.sportingo.api.magasin.controller.MagasinController;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class MagasinModelAssembler implements RepresentationModelAssembler<Magasin, EntityModel<Magasin>> {
    @Override
    public EntityModel<Magasin> toModel(Magasin magasin) {
        return EntityModel.of(magasin,
                linkTo(methodOn(MagasinController.class).getMagasin(magasin.getId())).withSelfRel(),
                linkTo(methodOn(MagasinController.class).getDifficultes()).withRel("magasins"));
    }
}