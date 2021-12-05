package fr.sportingo.api.controller;

import fr.sportingo.api.model.Category;
import fr.sportingo.api.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

/**
 * RestController pour les categories
 * @author Corentin Bringer
 * @version 1.0
 */
@RestController
public class CategoryController
{
    @Autowired
    private CategoryService categoryService;


    /**
     * Create - Enregistre une nouvelle categorie
     * @param category Objet Category
     * @return Objet Category
     */
    @PostMapping("/admin/category")
    public Category saveCategory(@RequestBody Category category)
    {
        return categoryService.saveCategory(category);
    }


    /**
     * Read - Retourne la liste de toutes les categories
     * @return Objet Category
     */
    @GetMapping("/category")
    public Iterable<Category> getCategories()
    {
        return categoryService.getCategories();
    }


    /**
     * Read - Retourne une categorie en fonction de l'id
     * @param id Integer id
     * @return Objet Category
     */
    @GetMapping("/category/{id}")
    public Category getCategory(@PathVariable("id") final Long id)
    {
        Optional<Category> category = categoryService.getCategory(id);

        return category.orElse(null);
    }


    /**
     * Update - Mets à jour une categorie en fonction de l'id
     * @param category Objet Category
     * @param id Integer id
     * @return Objet Category
     */
    @PutMapping("/admin/category/{id}")
    public Category updateCategory(@RequestBody Category category, @PathVariable("id") final Long id)
    {
        Optional<Category> c = categoryService.getCategory(id);

        if(c.isPresent()) {
            Category currentCategory = c.get();

            String label = category.getLabel();
            if(label != null) {
                currentCategory.setLabel(label);
            }

            categoryService.saveCategory(currentCategory);
            return currentCategory;
        } else {
            return null;
        }
    }


    /**
     * Delete - Supprime une categorie en fonction de l'id
     * @param id Integer id
     */
    @DeleteMapping("/admin/category/{id}")
    public void deleteCategory(@PathVariable("id") final Long id)
    {
        categoryService.deleteCategory(id);
    }
}
