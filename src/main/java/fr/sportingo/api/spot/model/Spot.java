package fr.sportingo.api.spot.model;

import fr.sportingo.api.difficulte.model.Difficulte;
import fr.sportingo.api.utilisateur.model.Utilisateur;
import fr.sportingo.api.sportMecanique.model.SportMecanique;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "spot")
public class Spot {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "image", length = 255)
    private String image;

    @Column(name = "video_youtube", length = 255)
    private String videoYoutube;

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

    @OneToOne
    @JoinColumn(name = "id_difficulte")
    private Difficulte difficulte;

    @ManyToMany
    @JoinTable(name = "spot_sport_mecanique",
                joinColumns = @JoinColumn(name = "id_spot"),
                inverseJoinColumns = @JoinColumn(name = "id_sport_mecanique"))
    private List<SportMecanique> sportMecaniques = new ArrayList<>();
}
