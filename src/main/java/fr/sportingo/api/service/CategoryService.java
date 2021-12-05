package fr.sportingo.api.service;

import fr.sportingo.api.model.Category;
import fr.sportingo.api.repository.CategoryRepository;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Service pour les categories
 * @author Corentin Bringer
 * @version 1.0
 */
@Data
@Service
public class CategoryService
{
    @Autowired
    private CategoryRepository categoryRepository;


    /**
     * Retourne une categorie en fonction de l'id
     * @param id Integer id
     * @return Objet Category
     */
    public Optional<Category> getCategory(final Long id)
    {
        return categoryRepository.findById(id);
    }


    /**
     * Retourne la liste de toutes les categories
     * @return Objet Category
     */
    public Iterable<Category> getCategories()
    {
        return categoryRepository.findAll();
    }


    /**
     * Supprime une categorie en fonction de l'id
     * @param id Integer id
     */
    public void deleteCategory(final Long id)
    {
        categoryRepository.deleteById(id);
    }


    /**
     * Enregistre une nouvelle categorie
     * @param category Objet Category
     * @return Objet Category
     */
    public Category saveCategory(Category category)
    {
        return categoryRepository.save(category);
    }
}
