package fr.sportingo.api.utilisateur.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "utilisateur")
@Inheritance(strategy = InheritanceType.JOINED)
public class Utilisateur {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "civilite", length = 8)
    private String civilite;

    @Column(name = "prenom", length = 50)
    private String prenom;

    @Column(name = "nom", length = 50)
    private String nom;

    @Column(name = "date_naissance")
    private Date dateNaissance;

    @Column(name = "email", length = 100, unique = true)
    private String email;

    @Column(name = "password", length = 255)
    private String password;

    @Column(name = "is_admin", length = 1)
    private Integer isAdmin;

    @Column(name = "id_desactive", length = 1)
    private Integer isDesactive;

//    @OneToMany(mappedBy = "utilisateur")
//    @JsonIgnore
//    private List<InscriptionUtilisateur> inscriptionUtilisateur = new ArrayList<>();
}
