package org.example.mapper.city;

import org.example.dto.city.CityDto;
import org.example.mapper.BaseMapper;
import org.example.model.City;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.CDI)
public interface CityMapper extends BaseMapper<CityDto, City> {
    CityMapper INSTANCE = Mappers.getMapper(CityMapper.class);
}