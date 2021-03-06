package fr.sportingo.api.difficulte.model;

import fr.sportingo.api.difficulte.statut.DifficulteStatut;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "difficulte")
public class Difficulte {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "libelle", length = 50)
    private String libelle;

    @Column(name = "statut", length = 9)
    @Enumerated(EnumType.STRING)
    private DifficulteStatut statut;
}
