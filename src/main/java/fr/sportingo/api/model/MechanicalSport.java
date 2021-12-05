package fr.sportingo.api.model;

import lombok.Data;

import javax.persistence.*;

/**
 * Entite pour les sports mecaniques
 * @author Corentin Bringer
 * @version 1.0
 */
@Data
@Entity
@Table(name = "mechanical_sport")
public class MechanicalSport
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "label", length = 50)
    private String label;
}
