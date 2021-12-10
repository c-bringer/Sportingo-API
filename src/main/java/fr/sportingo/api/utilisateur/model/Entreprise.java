package fr.sportingo.api.utilisateur.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "entreprise")
public class Entreprise extends Utilisateur {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nom_entreprise", length = 50)
    private String nomEntreprise;

    @Column(name = "telephone", length = 14)
    private String telephone;

    @Column(name = "siret", length = 17)
    private String siret;

    @Column(name = "numero_tva", length = 15)
    private String numeroTva;
}
