package fr.sportingo.api.utilisateur.controller;

import fr.sportingo.api.security.AuthenticationRequest;
import fr.sportingo.api.security.AuthenticationResponse;
import fr.sportingo.api.security.JwtUtil;
import fr.sportingo.api.utilisateur.exception.UtilisateurNotFoundException;
import fr.sportingo.api.utilisateur.model.Utilisateur;
import fr.sportingo.api.utilisateur.model.UtilisateurModelAssembler;
import fr.sportingo.api.utilisateur.service.UtilisateurService;
import fr.sportingo.api.utilisateur.statut.UtilisateurStatut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.MediaTypes;
import org.springframework.hateoas.mediatype.problem.Problem;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
public class UtilisateurController {
    @Autowired
    private UtilisateurService utilisateurService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private PasswordEncoder passwordEncoder;

    private final UtilisateurModelAssembler assembler;

    UtilisateurController(UtilisateurModelAssembler assembler) {
        this.assembler = assembler;
    }

    @PostMapping("/inscription-utilisateur")
    public ResponseEntity<?> inscriptionUtilisateur(@RequestBody Utilisateur inscription) {
        if(utilisateurService.loadUserByUsername(inscription.getUsername()) != null) {
            return ResponseEntity.status(HttpStatus.METHOD_NOT_ALLOWED)
                    .header(HttpHeaders.CONTENT_TYPE, MediaTypes.HTTP_PROBLEM_DETAILS_JSON_VALUE)
                    .body(Problem.create()
                            .withTitle("Méthode non autorisée")
                            .withDetail("Un compte est déjà rattaché à cette adresse mail."));
        }

        if(inscription.getPassword().length() < 8) {
            return ResponseEntity.status(HttpStatus.METHOD_NOT_ALLOWED)
                    .header(HttpHeaders.CONTENT_TYPE, MediaTypes.HTTP_PROBLEM_DETAILS_JSON_VALUE)
                    .body(Problem.create()
                            .withTitle("Méthode non autorisée")
                            .withDetail("Mot de passe doit avoir au minimum 8 caractères"));
        }

        inscription.setPassword(passwordEncoder.encode(inscription.getPassword()));

        EntityModel<Utilisateur> entityModel = assembler.toModel(utilisateurService.saveUtilisateur(inscription));

        return ResponseEntity.status(HttpStatus.CREATED)
                .header(HttpHeaders.CONTENT_TYPE, MediaTypes.HTTP_PROBLEM_DETAILS_JSON_VALUE)
                .body(Problem.create()
                        .withTitle("Utilisateur crée"));
    }

    @PostMapping("/connexion")
    public ResponseEntity<?> creationTokenConnexion(@RequestBody AuthenticationRequest authenticationRequest)
            throws Exception {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                    authenticationRequest.getUsername(), authenticationRequest.getPassword()));
        } catch(BadCredentialsException e) {
            throw new Exception("Utilisateur ou mot de passe incorrect(s)");

        }

        final UserDetails userDetails = utilisateurService.loadUserByUsername(authenticationRequest.getUsername());
        final String jwt = jwtUtil.generateToken(userDetails);

        return ResponseEntity.ok(new AuthenticationResponse(jwt));
    }









    @PostMapping("/public/utilisateur/inscription")
    public Utilisateur saveUtilisateur(@RequestBody Utilisateur utilisateur) {
        return utilisateurService.saveUtilisateur(utilisateur);
    }

    @GetMapping("/private-scoped/admin/utilisateur/liste-utilisateur")
    public CollectionModel<EntityModel<Utilisateur>> getUtilisateurs() {
        List<EntityModel<Utilisateur>> utilisateurs = utilisateurService.getUtilisateurs().stream()
                .map(assembler::toModel)
                .collect(Collectors.toList());

        return CollectionModel.of(utilisateurs, linkTo(methodOn(UtilisateurController.class).getUtilisateurs())
                .withSelfRel());
    }

    @GetMapping("/public/utilisateur/{uuid}")
    public ResponseEntity<?> getUtilisateur(@PathVariable("uuid") final UUID uuid) {
        Utilisateur utilisateur = utilisateurService.getUtilisateur(uuid)
                .orElseThrow(() -> new UtilisateurNotFoundException(uuid));

        if(utilisateur.getStatut() == UtilisateurStatut.ACTIVE) {
            return ResponseEntity.ok(assembler.toModel(utilisateur));
        }

        return ResponseEntity.status(HttpStatus.METHOD_NOT_ALLOWED)
                .header(HttpHeaders.CONTENT_TYPE, MediaTypes.HTTP_PROBLEM_DETAILS_JSON_VALUE)
                .body(Problem.create()
                        .withTitle("Méthode non autorisée")
                        .withDetail("Vous ne pouvez pas accéder à un utilisateur qui possède le statut : "
                                + utilisateur.getStatut()));
    }

    @PutMapping("/private/utilisateur/modifier/{id}")
    public Utilisateur updateUtilisateur(@PathVariable("id") final UUID uuid, @RequestBody Utilisateur utilisateur) {
        Optional<Utilisateur> u = utilisateurService.getUtilisateur(uuid);

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

            utilisateurService.saveUtilisateur(currentUtilisateur);
            return currentUtilisateur;
        } else {
            return null;
        }
    }

    @DeleteMapping("/private/utilisateur/supprimer/{id}")
    public void deleteUtilisateur(@PathVariable("id") final UUID id) {
        utilisateurService.deleteUtilisateur(id);
    }
}
