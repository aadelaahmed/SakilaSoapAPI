package org.example.mapper;

import org.example.dto.FilmDto;
import org.example.mapper.BaseMapper;
import org.example.model.Film;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

/*@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE,
        componentModel = MappingConstants.ComponentModel.CDI)*/
@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE,
        componentModel = MappingConstants.ComponentModel.CDI)
public interface FilmMapper extends BaseMapper<FilmDto, Film> {
    FilmMapper INSTANCE = Mappers.getMapper(FilmMapper.class);
}