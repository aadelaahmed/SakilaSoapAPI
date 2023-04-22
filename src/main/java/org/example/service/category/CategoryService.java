package org.example.service.category;

import org.example.dto.CategoryDto;
import org.example.mapper.CategoryMapper;
import org.example.model.Category;
import org.example.repository.CategoryRepository;
import org.example.service.BaseService;

public class CategoryService extends BaseService<Category,CategoryDto> {
    private CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository, CategoryMapper categoryMapper) {
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

