package org.example.service.category;

import org.example.dto.CategoryDto;
import org.example.dto.FilmDto;

import java.util.List;

public interface CategoryService {
    List<CategoryDto> getAllCategories();
    CategoryDto getCategoryById(Integer id);
    CategoryDto createCategory(CategoryDto categoryDto);
    CategoryDto updateCategory(Integer id, CategoryDto categoryDto);
    void deleteCategory(Integer id);
    boolean addFilmToCategory(Integer filmId,Integer categoryId);
}
