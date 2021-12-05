package fr.sportingo.api.model;

import lombok.Data;

import javax.persistence.*;

/**
 * Entite pour les inscriptions
 * @author Corentin Bringer
 * @version 1.0
 */
@Data
@Entity
@Table(name = "user_registration")
public class UserRegistration
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_user")
    private User user;

    @ManyToOne
    @JoinColumn(name = "id_event")
    private Event event;

    @Column(name = "is_accepted", length = 1)
    private Integer isAccepted;
}
