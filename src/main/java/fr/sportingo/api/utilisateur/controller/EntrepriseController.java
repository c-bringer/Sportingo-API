package fr.sportingo.api.utilisateur.controller;

import fr.sportingo.api.utilisateur.model.Entreprise;
import fr.sportingo.api.utilisateur.service.EntrepriseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.Optional;

@RestController
public class EntrepriseController {

    @Autowired
    private EntrepriseService entrepriseService;

    @PostMapping("/user-company")
    public Entreprise saveEntreprise(@RequestBody Entreprise entreprise) {
        return entrepriseService.saveEntreprise(entreprise);
    }

    @GetMapping("/user-company")
    public Iterable<Entreprise> getEntreprises() {
        return entrepriseService.getEntreprises();
    }

    @GetMapping("/user-company/{id}")
    public Entreprise getEntreprise(@PathVariable("id") final Long id) {
        Optional<Entreprise> user = entrepriseService.getEntreprise(id);

        return user.orElse(null);
    }

    @PutMapping("/user-company/{id}")
    public Entreprise updateEntreprise(@PathVariable("id") final Long id, @RequestBody Entreprise entreprise) {
        Optional<Entreprise> e = entrepriseService.getEntreprise(id);

        if(e.isPresent()) {
            Entreprise currentEntreprise = e.get();

            String civilite = entreprise.getCivilite();
            if(civilite != null) {
                currentEntreprise.setCivilite(civilite);
            }

            String prenom = entreprise.getPrenom();
            if(prenom != null) {
                currentEntreprise.setPrenom(prenom);
            }

            String nom = entreprise.getNom();
            if(nom != null) {
                currentEntreprise.setNom(nom);
            }

            Date dateNaissance = entreprise.getDateNaissance();
            if(dateNaissance != null) {
                currentEntreprise.setDateNaissance(dateNaissance);
            }

            String email = entreprise.getEmail();
            if(email != null) {
                currentEntreprise.setEmail(email);
            }

            String password = entreprise.getPassword();
            if(password != null) {
                currentEntreprise.setPassword(password);
            }

            Integer isAdmin = entreprise.getIsAdmin();
            if(isAdmin != null) {
                currentEntreprise.setIsAdmin(isAdmin);
            }

            Integer isDesactive = entreprise.getIsDesactive();
            if(isDesactive != null) {
                currentEntreprise.setIsDesactive(isDesactive);
            }

            String nomEntreprise = entreprise.getNomEntreprise();
            if(nomEntreprise != null) {
                currentEntreprise.setNomEntreprise(nomEntreprise);
            }

            String telephone = entreprise.getTelephone();
            if(telephone != null) {
                currentEntreprise.setTelephone(telephone);
            }

            String siret = entreprise.getSiret();
            if(siret != null) {
                currentEntreprise.setSiret(siret);
            }

            String numeroTva = entreprise.getNumeroTva();
            if(numeroTva != null) {
                currentEntreprise.setNumeroTva(numeroTva);
            }

            entrepriseService.saveEntreprise(currentEntreprise);
            return currentEntreprise;
        } else {
            return null;
        }
    }

    @DeleteMapping("/user-company/{id}")
    public void deleteEntreprise(@PathVariable("id") final Long id) {
        entrepriseService.deleteEntreprise(id);
    }
}
