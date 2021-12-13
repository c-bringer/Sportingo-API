package fr.sportingo.api.utilisateur.model;

import fr.sportingo.api.evenement.model.Evenement;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "inscription_utilisateur")
public class InscriptionUtilisateur {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_utilisateur")
    private Utilisateur utilisateur;

    @ManyToOne
    @JoinColumn(name = "id_evenement")
    private Evenement evenement;

    @Column(name = "is_accepte", length = 1)
    private Integer isAccepte;
}
