package fr.sportingo.api.service;

import fr.sportingo.api.model.UserCompany;
import fr.sportingo.api.repository.UserCompanyRepository;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Service pour les entreprises
 * @author Corentin Bringer
 * @version 1.0
 */
@Data
@Service
public class UserCompanyService
{
    @Autowired
    private UserCompanyRepository userCompanyRepository;


    /**
     * Retourne une entreprise en fonction de l'id
     * @param id Integer id
     * @return Objet UserCompany
     */
    public Optional<UserCompany> getUserCompany(final Long id)
    {
        return userCompanyRepository.findById(id);
    }


    /**
     * Retourne la liste des entreprises
     * @return Objet UserCompany
     */
    public Iterable<UserCompany> getUsersCompanies()
    {
        return userCompanyRepository.findAll();
    }


    /**
     * Supprime une entreprise en fonction de l'id
     * @param id Integer id
     */
    public void deleteUserCompany(final Long id)
    {
        userCompanyRepository.deleteById(id);
    }


    /**
     * Enregistre une nouvelle entreprise
     * @param userCompany Objet UserCompany
     * @return Objet UserCompany
     */
    public UserCompany saveUserCompany(UserCompany userCompany)
    {
        return userCompanyRepository.save(userCompany);
    }
}
