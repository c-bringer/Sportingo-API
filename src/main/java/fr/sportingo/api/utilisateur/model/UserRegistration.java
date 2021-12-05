package fr.sportingo.api.utilisateur.model;

import fr.sportingo.api.evenement.model.Evenement;
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
    private Evenement evenement;

    @Column(name = "is_accepted", length = 1)
    private Integer isAccepted;
}
