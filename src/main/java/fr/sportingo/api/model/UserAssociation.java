package fr.sportingo.api.model;

import lombok.Data;

import javax.persistence.*;

/**
 * Entite pour les associations
 * @author Corentin Bringer
 * @version 1.0
 */
@Data
@Entity
@Table(name = "user_association")
public class UserAssociation extends User
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "label", length = 50)
    private String label;

    @Column(name = "phone", length = 14)
    private String phone;
}
