package fr.sportingo.api.utilisateur.model;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "entreprise")
public class Entreprise extends Utilisateur {
    @Id
    @GeneratedValue(generator = "uuid2")
    @Type(type = "uuid-char")
    private UUID uuid = UUID.randomUUID();

    @Column(name = "nom_entreprise", length = 50)
    private String nomEntreprise;

    @Column(name = "telephone", length = 14)
    private String telephone;

    @Column(name = "siret", length = 17)
    private String siret;

    @Column(name = "numero_tva", length = 15)
    private String numeroTva;
}
