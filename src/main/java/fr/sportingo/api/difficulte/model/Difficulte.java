package fr.sportingo.api.difficulte.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

/**
 * Entite pour les difficultes
 * @author Corentin Bringer
 * @version 1.0
 */
@Getter
@Setter
@Entity
@Table(name = "difficulte")
public class Difficulte
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "libelle", length = 50)
    private String libelle;
}
