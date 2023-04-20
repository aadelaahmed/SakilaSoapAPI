package org.example.mapper.country;

import org.example.dto.CountryDto;
import org.example.mapper.BaseMapper;
import org.example.model.Country;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.CDI)
public interface CountryMapper extends BaseMapper<CountryDto, Country> {
    CountryMapper INSTANCE = Mappers.getMapper(CountryMapper.class);
}