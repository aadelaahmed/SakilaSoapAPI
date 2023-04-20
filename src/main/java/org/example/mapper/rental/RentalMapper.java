package org.example.mapper.rental;

import org.example.dto.rental.RentalDto;
import org.example.mapper.BaseMapper;
import org.example.model.Rental;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

/*@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE,
        componentModel = MappingConstants.ComponentModel.CDI,
        uses = {InventoryMapper.class, StaffMapper.class})*/
@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE,
        componentModel = MappingConstants.ComponentModel.CDI)

public interface RentalMapper extends BaseMapper<RentalDto, Rental> {
    RentalMapper INSTANCE = Mappers.getMapper(RentalMapper.class);
}