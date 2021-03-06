package fr.sportingo.api.evenement.model;

import fr.sportingo.api.motifCirculation.model.MotifCirculation;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "evenement_circulation")
public class EvenementCirculation extends Evenement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private MotifCirculation motifCirculation;
}
