package fr.sportingo.api.evenement.model;

import fr.sportingo.api.sportMecanique.model.SportMecanique;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "evenement_sportif")
public class EvenementSportif extends Evenement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nombre_places", length = 11)
    private Integer nombrePlaces;

    @OneToOne
    @JoinColumn(name = "id_sport_mecanique")
    private SportMecanique sportMecanique;
}
