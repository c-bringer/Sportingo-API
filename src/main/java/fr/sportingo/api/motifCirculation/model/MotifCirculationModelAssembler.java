package fr.sportingo.api.motifCirculation.model;

import fr.sportingo.api.motifCirculation.controller.MotifCirculationController;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class MotifCirculationModelAssembler implements RepresentationModelAssembler<MotifCirculation, EntityModel<MotifCirculation>> {
    @Override
    public EntityModel<MotifCirculation> toModel(MotifCirculation motifCirculation) {
        return EntityModel.of(motifCirculation,
                linkTo(methodOn(MotifCirculationController.class).getMotifCirculation(motifCirculation.getId())).withSelfRel(),
                linkTo(methodOn(MotifCirculationController.class).getMotifsCirculation()).withRel("motifsCirculation"));
    }
}
