package fr.sportingo.api.magasin.model;

import fr.sportingo.api.utilisateur.model.Utilisateur;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "magasin")
public class Magasin {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nom", length = 50)
    private String nom;

    @Column(name = "adresse")
    private String adresse;

    @Column(name = "code_postal", length = 5)
    private String codePostal;

    @Column(name = "ville", length = 50)
    private String ville;

    @OneToOne
    @JoinColumn(name = "id_utilisateur")
    private Utilisateur utilisateur;
}
