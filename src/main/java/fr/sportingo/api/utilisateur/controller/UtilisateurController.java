package fr.sportingo.api.utilisateur.controller;

import fr.sportingo.api.utilisateur.model.Utilisateur;
import fr.sportingo.api.utilisateur.service.UtilisateurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.Optional;

@RestController
public class UtilisateurController {

    @Autowired
    private UtilisateurService utilisateurService;

    @PostMapping("/public/utilisateur/inscription")
    public Utilisateur saveUtilisateur(@RequestBody Utilisateur utilisateur) {
        return utilisateurService.saveUtilisateur(utilisateur);
    }

    @GetMapping("/private-scoped/admin/utilisateur/liste-utilisateur")
    public Iterable<Utilisateur> getUtilisateurs() {
        return utilisateurService.getUtilisateurs();
    }

    @GetMapping("/public/utilisateur/{id}")
    public Utilisateur getUtilisateur(@PathVariable("id") final Long id) {
        Optional<Utilisateur> utilisateur = utilisateurService.getUtilisateur(id);

        return utilisateur.orElse(null);
    }

    @PutMapping("/private/utilisateur/modifier/{id}")
    public Utilisateur updateUtilisateur(@PathVariable("id") final Long id, @RequestBody Utilisateur utilisateur) {
        Optional<Utilisateur> u = utilisateurService.getUtilisateur(id);

        if(u.isPresent()) {
            Utilisateur currentUtilisateur = u.get();

            String civilite = utilisateur.getCivilite();
            if(civilite != null) {
                currentUtilisateur.setCivilite(civilite);
            }

            String prenom = utilisateur.getPrenom();
            if(prenom != null) {
                currentUtilisateur.setPrenom(prenom);
            }

            String nom = utilisateur.getNom();
            if(nom != null) {
                currentUtilisateur.setNom(nom);
            }

            Date dateNaissance = utilisateur.getDateNaissance();
            if(dateNaissance != null) {
                currentUtilisateur.setDateNaissance(dateNaissance);
            }

            String email = utilisateur.getEmail();
            if(email != null) {
                currentUtilisateur.setEmail(email);
            }

            String password = utilisateur.getPassword();
            if(password != null) {
                currentUtilisateur.setPassword(password);
            }

            Integer isAdmin = utilisateur.getIsAdmin();
            if(isAdmin != null) {
                currentUtilisateur.setIsAdmin(isAdmin);
            }

            Integer isDesactive = utilisateur.getIsDesactive();
            if(isDesactive != null) {
                currentUtilisateur.setIsDesactive(isDesactive);
            }

            utilisateurService.saveUtilisateur(currentUtilisateur);
            return currentUtilisateur;
        } else {
            return null;
        }
    }

    @DeleteMapping("/private/utilisateur/supprimer/{id}")
    public void deleteUtilisateur(@PathVariable("id") final Long id) {
        utilisateurService.deleteUtilisateur(id);
    }
}
