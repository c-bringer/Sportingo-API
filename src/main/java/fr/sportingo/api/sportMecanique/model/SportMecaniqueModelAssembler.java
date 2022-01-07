package fr.sportingo.api.sportMecanique.model;

import fr.sportingo.api.sportMecanique.controller.SportMecaniqueController;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class SportMecaniqueModelAssembler implements RepresentationModelAssembler<SportMecanique, EntityModel<SportMecanique>> {
    @Override
    public EntityModel<SportMecanique> toModel(SportMecanique sportMecanique) {
        return EntityModel.of(sportMecanique,
                linkTo(methodOn(SportMecaniqueController.class).getSportMecanique(sportMecanique.getId())).withSelfRel(),
                linkTo(methodOn(SportMecaniqueController.class).getSportsMecaniques()).withRel("sportsMecaniques"));
    }
}