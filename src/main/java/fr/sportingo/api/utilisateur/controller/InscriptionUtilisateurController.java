package fr.sportingo.api.utilisateur.controller;

import fr.sportingo.api.utilisateur.model.InscriptionUtilisateur;
import fr.sportingo.api.utilisateur.service.InscriptionUtilisateurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

//@RestController
public class InscriptionUtilisateurController {

    @Autowired
    private InscriptionUtilisateurService inscriptionUtilisateurService;

    @PostMapping("/user-registration")
    public InscriptionUtilisateur enregistrerInscriptionUtilisateur(@RequestBody InscriptionUtilisateur inscriptionUtilisateur) {
        return inscriptionUtilisateurService.enregistrerInscriptionUtilisateur(inscriptionUtilisateur);
    }

    @GetMapping("/user-registration")
    public Iterable<InscriptionUtilisateur> getInscriptionsUtilisateurs() {
        return inscriptionUtilisateurService.getInscriptionsUtilisateurs();
    }

    @GetMapping("/user-registration/{id}")
    public InscriptionUtilisateur getInscriptionUtilisateur(@PathVariable("id") final Long id) {
        Optional<InscriptionUtilisateur> inscription = inscriptionUtilisateurService.getInscriptionUtilisateur(id);

        return inscription.orElse(null);
    }

    @PutMapping("/user-registration/{id}")
    public InscriptionUtilisateur modifierInscriptionUtilisateur(@PathVariable("id") final Long id, @RequestBody InscriptionUtilisateur inscriptionUtilisateur) {
        Optional<InscriptionUtilisateur> iu = inscriptionUtilisateurService.getInscriptionUtilisateur(id);

        if(iu.isPresent()) {
            InscriptionUtilisateur currentInscriptionUtilisateur = iu.get();

            Integer isAccepte = inscriptionUtilisateur.getIsAccepte();
            if(isAccepte != null) {
                currentInscriptionUtilisateur.setIsAccepte(isAccepte);
            }

            inscriptionUtilisateurService.enregistrerInscriptionUtilisateur(currentInscriptionUtilisateur);
            return currentInscriptionUtilisateur;
        } else {
            return null;
        }
    }

    @DeleteMapping("/user-registration/{id}")
    public void supprimerInscriptionUtilisateur(@PathVariable("id") final Long id) {
        inscriptionUtilisateurService.supprimerInscriptionUtilisateur(id);
    }
}
