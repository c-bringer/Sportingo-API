package fr.sportingo.api.sportMecanique.model;

import lombok.Data;

import javax.persistence.*;

/**
 * Entite pour les sports mecaniques
 * @author Corentin Bringer
 * @version 1.0
 */
@Data
@Entity
@Table(name = "sport_mecanique")
public class SportMecanique
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "libelle", length = 50)
    private String libelle;
}
