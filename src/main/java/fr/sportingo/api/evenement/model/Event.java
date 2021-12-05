package fr.sportingo.api.evenement.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import fr.sportingo.api.utilisateur.model.User;
import fr.sportingo.api.utilisateur.model.UserRegistration;
import fr.sportingo.api.sportMecanique.model.SportMecanique;
import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Entite pour les evenements
 * @author Corentin Bringer
 * @version 1.0
 */
@Data
@Entity
@Table(name = "event")
public class Event
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "image", length = 255)
    private String image;

    @Column(name = "name", length = 50)
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "date_hours")
    private Date dateHours;

    @Column(name = "minimum_birthday")
    private Date minimumBirthday;

    @Column(name = "number_places", length = 11)
    private Integer nbPlaces;

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
    @JoinColumn(name = "id_mechanical_sport")
    private SportMecanique sportMecanique;

//    @ManyToMany
//    @JoinTable(name = "event_category",
//            joinColumns = @JoinColumn(name = "id_event"),
//            inverseJoinColumns = @JoinColumn(name = "id_category"))
//    private List<Category> categories = new ArrayList<>();

    @OneToMany(mappedBy = "event")
    @JsonIgnore
    private List<UserRegistration> userRegistration = new ArrayList<>();
}
