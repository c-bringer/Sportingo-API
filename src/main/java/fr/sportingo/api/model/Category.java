package fr.sportingo.api.model;

import lombok.Data;

import javax.persistence.*;

/**
 * Entite pour les categories
 * @author Corentin Bringer
 * @version 1.0
 */
@Data
@Entity
@Table(name = "category")
public class Category
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "label", length = 50)
    private String label;
}
