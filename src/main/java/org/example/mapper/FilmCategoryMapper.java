package org.example.mapper;

import org.example.dto.FilmCategoryDto;
import org.example.model.FilmCategory;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;

/*@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE,
        componentModel = MappingConstants.ComponentModel.CDI,
        uses = {FilmCategoryIdMapper.class, FilmMapper.class})*/
@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE,
        componentModel = MappingConstants.ComponentModel.CDI)
public interface FilmCategoryMapper extends BaseMapper<FilmCategoryDto, FilmCategory> {
}