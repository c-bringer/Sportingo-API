package fr.sportingo.api.evenement.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import fr.sportingo.api.utilisateur.model.InscriptionUtilisateur;
import fr.sportingo.api.utilisateur.model.Utilisateur;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "evenement")
@Inheritance(strategy = InheritanceType.JOINED)
public class Evenement
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "image")
    private String image;

    @Column(name = "nom", length = 50)
    private String nom;

    @Column(name = "description")
    private String description;

    @Column(name = "longitude")
    private Double longitude;

    @Column(name = "latitude")
    private Double latitude;

    @Column(name = "is_verifie", length = 1)
    private Integer isVerifie;

    @Column(name = "is_desactive", length = 1)
    private Integer isDesactive;

    @OneToOne
    @JoinColumn(name = "id_utilisateur")
    private Utilisateur utilisateur;

    @OneToMany(mappedBy = "evenement")
    @JsonIgnore
    private List<InscriptionUtilisateur> inscriptionUtilisateur = new ArrayList<>();
}
