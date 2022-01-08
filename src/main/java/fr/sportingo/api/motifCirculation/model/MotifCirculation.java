package fr.sportingo.api.motifCirculation.model;

import fr.sportingo.api.motifCirculation.status.MotifCirculationStatus;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "motif_circulation")
public class MotifCirculation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "libelle", length = 50)
    private String libelle;

    @Column(name = "status", length = 9)
    @Enumerated(EnumType.STRING)
    private MotifCirculationStatus status;
}
