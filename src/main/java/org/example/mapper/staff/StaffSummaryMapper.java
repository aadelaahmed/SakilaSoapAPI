package org.example.mapper.staff;

import org.example.dto.staff.StaffDto;
import org.example.dto.staff.StaffSummaryDto;
import org.example.dto.store.StoreSummaryDto;
import org.example.mapper.BaseMapper;
import org.example.mapper.store.StoreSummaryMapper;
import org.example.model.Staff;
import org.example.model.Store;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE,
        componentModel = MappingConstants.ComponentModel.CDI)
public interface StaffSummaryMapper extends BaseMapper<StaffSummaryDto, Staff> {
    StaffSummaryMapper INSTANCE = Mappers.getMapper(StaffSummaryMapper.class);

    @Mapping(target = "id",source = "id")
    @Mapping(target = "lastName",source = "lastName")
    @Mapping(target = "firstName",source = "firstName")
    @Mapping(target = "address.address",source = "staffAddress")
    @Mapping(target = "address.phone",source = "staffPhone")
    @Mapping(target = "address.city.city",source = "staffCity")
    @Mapping(target = "store.address.address",source = "storeAddress")
    @Mapping(target = "store.address.city.city",source = "storeCity")
    @Mapping(target = "store.address.phone",source = "storePhone")
    @Mapping(target = "active",source = "active")
    @Mapping(target = "email",source = "email")
    @Mapping(target = "username",source = "username")
    Staff toEntity(StaffSummaryDto staffSummaryDto);


    @Mapping(source = "id",target = "id")
    @Mapping(source = "lastName",target = "lastName")
    @Mapping(source = "firstName",target = "firstName")
    @Mapping(source = "address.address",target = "staffAddress")
    @Mapping(source = "address.phone",target = "staffPhone")
    @Mapping(source = "address.city.city",target = "staffCity")
    @Mapping(source = "store.address.address",target = "storeAddress")
    @Mapping(source = "store.address.city.city",target = "storeCity")
    @Mapping(source = "store.address.phone",target = "storePhone")
    @Mapping(source = "active",target = "active")
    @Mapping(source = "email",target = "email")
    @Mapping(source = "username",target = "username")
    StaffSummaryDto toDto(Staff staff);

    @Named("partialUpdate")
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "id",source = "id")
    @Mapping(target = "lastName",source = "lastName")
    @Mapping(target = "firstName",source = "firstName")
    @Mapping(target = "address.address",source = "staffAddress")
    @Mapping(target = "address.phone",source = "staffPhone")
    @Mapping(target = "address.city.city",source = "staffCity")
    @Mapping(target = "store.address.address",source = "storeAddress")
    @Mapping(target = "store.address.city.city",source = "storeCity")
    @Mapping(target = "store.address.phone",source = "storePhone")
    @Mapping(target = "active",source = "active")
    @Mapping(target = "email",source = "email")
    @Mapping(target = "username",source = "username")
    void partialUpdate(@MappingTarget Staff entity, StaffSummaryDto dto);
}
