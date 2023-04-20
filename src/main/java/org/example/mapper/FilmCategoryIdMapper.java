package org.example.mapper;

import org.example.mapper.BaseMapper;
import org.example.model.FilmCategoryId;
import org.example.model.FilmCategoryIdDto;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;

/*@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE,
        componentModel = MappingConstants.ComponentModel.CDI)*/
@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE,
        componentModel = MappingConstants.ComponentModel.CDI)

public interface FilmCategoryIdMapper extends BaseMapper<FilmCategoryIdDto, FilmCategoryId> {
}