package fr.sportingo.api.magasin.model;

import fr.sportingo.api.magasin.status.MagasinStatus;
import fr.sportingo.api.utilisateur.model.Utilisateur;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "magasin")
public class Magasin {

//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "id", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "id", columnDefinition = "VARCHAR(255)")
    private UUID id;

    @Column(name = "nom", length = 50)
    private String nom;

    @Column(name = "telephone", length = 14)
    private String telephone;

    @Column(name = "email", length = 100)
    private String email;

    @Column(name = "adresse")
    private String adresse;

    @Column(name = "code_postal", length = 5)
    private String codePostal;

    @Column(name = "ville", length = 50)
    private String ville;

    @Column(name = "status", length = 10)
    private MagasinStatus status;

    @OneToOne
    @JoinColumn(name = "id_utilisateur")
    private Utilisateur utilisateur;
}
