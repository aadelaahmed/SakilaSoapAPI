package org.example.service.category;

import org.example.dto.CategoryDto;
import org.example.dto.FilmDto;
import org.example.mapper.BaseMapper;
import org.example.mapper.CategoryMapper;
import org.example.mapper.FilmMapper;
import org.example.model.Category;
import org.example.repository.BaseRepository;
import org.example.repository.CategoryRepository;
import org.example.service.BaseService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.ws.rs.NotFoundException;

import java.util.List;
import java.util.Optional;

public class CategoryServiceImpl extends BaseService<Category,CategoryDto> {
    private CategoryRepository categoryRepository;

    public CategoryServiceImpl(CategoryRepository categoryRepository, CategoryMapper categoryMapper) {
        super(categoryRepository, categoryMapper);
        this.categoryRepository = categoryRepository;
    }

    @Override
    protected Class<Category> getEntityClass() {
        return Category.class;
    }

    @Override
    protected Class<CategoryDto> getDtoClass() {
        return CategoryDto.class;
    }

    public boolean addFilmToCategory(Integer filmId, Integer categoryId) {
        return categoryRepository.addFilmToCategory(filmId,categoryId);
    }


}

