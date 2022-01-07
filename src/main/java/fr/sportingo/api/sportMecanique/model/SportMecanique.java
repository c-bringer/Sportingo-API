package fr.sportingo.api.sportMecanique.model;

import fr.sportingo.api.sportMecanique.status.SportMecaniqueStatus;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "sport_mecanique")
public class SportMecanique {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "libelle", length = 50)
    private String libelle;

    @Column(name = "status", length = 9)
    @Enumerated(EnumType.STRING)
    private SportMecaniqueStatus status;
}
