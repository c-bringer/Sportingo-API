package fr.sportingo.api.model;

import lombok.Data;

import javax.persistence.*;

/**
 * Entite pour les entreprises
 * @author Corentin Bringer
 * @version 1.0
 */
@Data
@Entity
@Table(name = "user_company")
public class UserCompany extends User
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "label", length = 50)
    private String label;

    @Column(name = "phone", length = 14)
    private String phone;

    @Column(name = "siret", length = 17)
    private String siret;

    @Column(name = "tva_number", length = 15)
    private String tvaNumber;
}
