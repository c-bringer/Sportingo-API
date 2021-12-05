package fr.sportingo.api.controller;

import fr.sportingo.api.model.UserCompany;
import fr.sportingo.api.service.UserCompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.Optional;

/**
 * RestController pour les entreprises
 * @author Corentin Bringer
 * @version 1.0
 */
@RestController
public class UserCompanyController
{
    @Autowired
    private UserCompanyService userCompanyService;


    /**
     * Create - Enregistre une nouvelle entreprise
     * @param userCompany Objet UserCompany
     * @return Objet UserCompany
     */
    @PostMapping("/user-company")
    public UserCompany saveUserCompany(@RequestBody UserCompany userCompany)
    {
        return userCompanyService.saveUserCompany(userCompany);
    }


    /**
     * Read - Retourne la liste de toutes les entreprises
     * @return Objet UserCompany
     */
    @GetMapping("/user-company")
    public Iterable<UserCompany> getUsersCompagnies()
    {
        return userCompanyService.getUsersCompanies();
    }

    /**
     * Read - Retourne une entreprise en fonction de l'id
     * @param id Integer id
     * @return Objet UserCompany
     */
    @GetMapping("/user-company/{id}")
    public UserCompany getUserCompany(@PathVariable("id") final Long id)
    {
        Optional<UserCompany> user = userCompanyService.getUserCompany(id);

        return user.orElse(null);
    }


    /**
     * Update - Mets à jour une entreprise en fonction de l'id
     * @param id Integer id
     * @param userCompany Objet UserCompany
     * @return Objet UserCompany
     */
    @PutMapping("/user-company/{id}")
    public UserCompany updateUserCompany(@PathVariable("id") final Long id, @RequestBody UserCompany userCompany)
    {
        Optional<UserCompany> uc = userCompanyService.getUserCompany(id);

        if(uc.isPresent()) {
            UserCompany currentUserCompany = uc.get();

            String civility = userCompany.getCivility();
            if(civility != null) {
                currentUserCompany.setCivility(civility);
            }

            String firstname = userCompany.getFirstname();
            if(firstname != null) {
                currentUserCompany.setFirstname(firstname);
            }

            String lastname = userCompany.getLastname();
            if(lastname != null) {
                currentUserCompany.setLastname(lastname);
            }

            Date birthday = userCompany.getBirthday();
            if(birthday != null) {
                currentUserCompany.setBirthday(birthday);
            }

            String email = userCompany.getEmail();
            if(email != null) {
                currentUserCompany.setEmail(email);
            }

            String password = userCompany.getPassword();
            if(password != null) {
                currentUserCompany.setPassword(password);
            }

            Integer isAdmin = userCompany.getIsAdmin();
            if(isAdmin != null) {
                currentUserCompany.setIsAdmin(isAdmin);
            }

            Integer isDisabled = userCompany.getIsDisabled();
            if(isDisabled != null) {
                currentUserCompany.setIsDisabled(isDisabled);
            }

            String label = userCompany.getLabel();
            if(label != null) {
                currentUserCompany.setLabel(label);
            }

            String phone = userCompany.getPhone();
            if(phone != null) {
                currentUserCompany.setPhone(phone);
            }

            String siret = userCompany.getSiret();
            if(siret != null) {
                currentUserCompany.setSiret(siret);
            }

            String tvaNumber = userCompany.getTvaNumber();
            if(tvaNumber != null) {
                currentUserCompany.setTvaNumber(tvaNumber);
            }

            userCompanyService.saveUserCompany(currentUserCompany);
            return currentUserCompany;
        } else {
            return null;
        }
    }


    /**
     * Delete - Supprime une entreprise en fonction de l'id
     * @param id Integer id
     */
    @DeleteMapping("/user-company/{id}")
    public void deleteUserCompany(@PathVariable("id") final Long id)
    {
        userCompanyService.deleteUserCompany(id);
    }
}
