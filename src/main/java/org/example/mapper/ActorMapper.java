package org.example.mapper;

import org.example.dto.ActorDto;
import org.example.mapper.BaseMapper;
import org.example.model.Actor;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

/*@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE,
        componentModel = MappingConstants.ComponentModel.CDI)*/
@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE,
        componentModel = MappingConstants.ComponentModel.CDI)
public interface ActorMapper extends BaseMapper<ActorDto, Actor> {
     ActorMapper INSTANCE = Mappers.getMapper(ActorMapper.class);
}