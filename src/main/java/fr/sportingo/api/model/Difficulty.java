package fr.sportingo.api.model;

import lombok.Data;

import javax.persistence.*;

/**
 * Entite pour les difficultes
 * @author Corentin Bringer
 * @version 1.0
 */
@Data
@Entity
@Table(name = "difficulty")
public class Difficulty
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "label", length = 50)
    private String label;
}
