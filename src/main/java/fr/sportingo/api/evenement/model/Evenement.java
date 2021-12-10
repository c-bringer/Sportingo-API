package fr.sportingo.api.evenement.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import fr.sportingo.api.utilisateur.model.InscriptionUtilisateur;
import fr.sportingo.api.utilisateur.model.Utilisateur;
import fr.sportingo.api.sportMecanique.model.SportMecanique;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Entite pour les evenements
 * @author Corentin Bringer
 * @version 1.0
 */
@Getter
@Setter
//@Entity
//@Table(name = "evenement")
public class Evenement
{
    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

//    @Column(name = "image", length = 255)
    private String image;

//    @Column(name = "name", length = 50)
    private String name;

//    @Column(name = "description")
    private String description;

//    @Column(name = "date_hours")
    private Date dateHours;

//    @Column(name = "minimum_birthday")
    private Date minimumBirthday;

//    @Column(name = "number_places", length = 11)
    private Integer nbPlaces;

//    @Column(name = "longitude")
    private Double longitude;

//    @Column(name = "latitude")
    private Double latitude;

//    @Column(name = "is_verified", length = 1)
    private Integer isVerified;

//    @Column(name = "is_disabled", length = 1)
    private Integer isDisabled;

//    @OneToOne
//    @JoinColumn(name = "id_user")
    private Utilisateur utilisateur;

//    @OneToOne
//    @JoinColumn(name = "id_mechanical_sport")
    private SportMecanique sportMecanique;

//    @ManyToMany
//    @JoinTable(name = "event_category",
//            joinColumns = @JoinColumn(name = "id_event"),
//            inverseJoinColumns = @JoinColumn(name = "id_category"))
//    private List<Category> categories = new ArrayList<>();

//    @OneToMany(mappedBy = "evenement")
//    @JsonIgnore
//    private List<InscriptionUtilisateur> inscriptionUtilisateur = new ArrayList<>();
}
