package org.example.mapper;

import org.example.dto.CategoryDto;
import org.example.model.Category;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

/*@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE,
        componentModel = MappingConstants.ComponentModel.CDI, uses = {FilmCategoryMapper.class})*/
@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE,
        componentModel = MappingConstants.ComponentModel.CDI)

public interface CategoryMapper extends BaseMapper<CategoryDto, Category> {
    CategoryMapper INSTANCE = Mappers.getMapper(CategoryMapper.class);

    @AfterMapping
    default void linkFilmCategories(@MappingTarget Category category) {
        category.getFilmCategories().forEach(filmCategory -> filmCategory.setCategory(category));
    }
}