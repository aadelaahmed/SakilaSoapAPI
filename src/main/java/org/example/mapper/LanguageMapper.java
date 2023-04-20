package org.example.mapper;

import org.example.dto.LanguageDto;
import org.example.mapper.BaseMapper;
import org.example.mapper.FilmMapper;
import org.example.model.Language;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

/*@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE,
        componentModel = MappingConstants.ComponentModel.CDI,
        uses = {FilmMapper.class, FilmMapper.class})*/
@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE,
        componentModel = MappingConstants.ComponentModel.CDI)

public interface LanguageMapper extends BaseMapper<LanguageDto, Language> {
    LanguageMapper INSTANCE = Mappers.getMapper(LanguageMapper.class);

    @AfterMapping
    default void linkFilms(@MappingTarget Language language) {
        language.getFilms().forEach(film -> film.setLanguage(language));
    }

    @AfterMapping
    default void linkOrgLangFilms(@MappingTarget Language language) {
        language.getOrgLangFilms().forEach(orgLangFilm -> orgLangFilm.setOriginalLanguage(language));
    }
}