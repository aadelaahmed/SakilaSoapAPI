package org.example.repository;

import jakarta.xml.ws.WebServiceException;
import org.example.model.Category;
import org.example.model.Film;
import org.example.model.FilmCategory;
import org.example.model.FilmCategoryId;
import org.example.util.Database;
import jakarta.persistence.EntityNotFoundException;

import java.time.Instant;

public class CategoryRepository extends BaseRepository<Category,Integer> {

    public CategoryRepository() {
        super(Category.class);
    }

    public boolean addFilmToCategory(Integer filmId, Integer categoryId) {
        return Database.doInTransaction(
                entityManager -> {
                    Category category = entityManager.find(Category.class, categoryId);
                    if (category == null) {
                        throw new WebServiceException("There is no category with this id");
                    }
                    Film film = entityManager.find(Film.class,filmId);
                    if (film == null)
                        throw new WebServiceException("There is no film with this id");
                    // Check if the film is already added to the category
                    boolean isFilmAlreadyInCategory = category.getFilmCategories().stream()
                            .anyMatch(filmCategory -> filmCategory.getFilm().getId().equals(filmId));
                    if (isFilmAlreadyInCategory) {
                        System.out.println("The film is already added to the category");
                        throw new WebServiceException("The film is already added to this category");
                    }
                    System.out.println("Start add the film to category");
                    FilmCategory filmCategory = new FilmCategory();
                    filmCategory.setFilm(film);
                    filmCategory.setCategory(category);
                    filmCategory.setLastUpdate(Instant.now());
                    //create new composite key for using the filmcategoryid class.
                    FilmCategoryId filmCategoryId = new FilmCategoryId();
                    filmCategoryId.setCategoryId(categoryId);
                    filmCategoryId.setFilmId(filmId);
                    //set the create composite key to filmcategory table as a new entry.
                    filmCategory.setId(filmCategoryId);
                    entityManager.persist(filmCategory);
                    System.out.println("the film was added to "+category.getName() +" category successfully");
                    return true;
                }
        );
    }


}
