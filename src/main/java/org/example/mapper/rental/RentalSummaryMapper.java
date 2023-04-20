package org.example.mapper.rental;

import org.example.dto.customer.CustomerSummaryDto;
import org.example.dto.rental.RentalSummaryDto;
import org.example.mapper.BaseMapper;
import org.example.model.Customer;
import org.example.model.Rental;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.CDI)
public interface RentalSummaryMapper extends BaseMapper<RentalSummaryDto, Rental> {
    RentalSummaryMapper INSTANCE = Mappers.getMapper(RentalSummaryMapper.class);

    @Mapping(target = "customerEmail",source = "customer.email")
    @Mapping(target = "storeId",source = "staff.store.id")
    @Mapping(target = "id",source = "id")
    @Mapping(target = "rentalDate",source = "rentalDate")
    @Mapping(target = "lastUpdate",source = "lastUpdate")
    @Mapping(target = "returnDate",source = "returnDate")
    @Mapping(target = "staffUserName",source = "staff.username")
    RentalSummaryDto toDto(Rental rental);


    @Mapping(source = "customerEmail",target = "customer.email")
    @Mapping(source = "storeId",target = "staff.store.id")
    @Mapping(source = "id",target = "id")
    @Mapping(source = "rentalDate",target = "rentalDate")
    @Mapping(source = "lastUpdate",target = "lastUpdate")
    @Mapping(source = "returnDate",target = "returnDate")
    @Mapping(source = "staffUserName",target = "staff.username")
    Rental toEntity(RentalSummaryDto rentalSummaryDto);


    @Named("partialUpdate")
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(source = "customerEmail",target = "customer.email")
    @Mapping(source = "storeId",target = "staff.store.id")
    @Mapping(source = "id",target = "id")
    @Mapping(source = "rentalDate",target = "rentalDate")
    @Mapping(source = "lastUpdate",target = "lastUpdate")
    @Mapping(source = "returnDate",target = "returnDate")
    @Mapping(source = "staffUserName",target = "staff.username")
    void partialUpdate(@MappingTarget Rental rental, RentalSummaryDto rentalSummaryDto);
}