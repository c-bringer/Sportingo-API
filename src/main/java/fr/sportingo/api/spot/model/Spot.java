package fr.sportingo.api.spot.model;

import fr.sportingo.api.difficulte.model.Difficulte;
import fr.sportingo.api.utilisateur.model.User;
import fr.sportingo.api.sportMecanique.model.MechanicalSport;
import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Entite pour les spots
 * @author Corentin Bringer
 * @version 1.0
 */
@Data
@Entity
@Table(name = "spot")
public class Spot
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "image", length = 255)
    private String image;

    @Column(name = "youtube_link", length = 255)
    private String youtubeLink;

    @Column(name = "name", length = 50)
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "longitude")
    private Double longitude;

    @Column(name = "latitude")
    private Double latitude;

    @Column(name = "is_verified", length = 1)
    private Integer isVerified;

    @Column(name = "is_disabled", length = 1)
    private Integer isDisabled;

    @OneToOne
    @JoinColumn(name = "id_user")
    private User user;

    @OneToOne
    @JoinColumn(name = "id_difficulty")
    private Difficulte difficulte;

    @ManyToMany
    @JoinTable(name = "spot_mechanical_sport",
                joinColumns = @JoinColumn(name = "id_spot"),
                inverseJoinColumns = @JoinColumn(name = "id_mechanical_sport"))
    private List<MechanicalSport> mechanicalSports = new ArrayList<>();
}
