package fr.sportingo.api.utilisateur.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Entite pour les utilisateurs
 * @author Corentin Bringer
 * @version 1.0
 */
@Data
@Entity
@Table(name = "user")
@Inheritance(strategy = InheritanceType.JOINED)
public class User
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "civility", length = 8)
    private String civility;

    @Column(name = "firstname", length = 50)
    private String firstname;

    @Column(name = "lastname", length = 50)
    private String lastname;

    @Column(name = "birthday")
    private Date birthday;

    @Column(name = "email", length = 100, unique = true)
    private String email;

    @Column(name = "password", length = 255)
    private String password;

    @Column(name = "is_admin", length = 1)
    private Integer isAdmin;

    @Column(name = "is_disabled", length = 1)
    private Integer isDisabled;

    @OneToMany(mappedBy = "user")
    @JsonIgnore
    private List<UserRegistration> userRegistration = new ArrayList<>();
}
