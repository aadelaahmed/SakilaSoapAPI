package org.example.mapper.store;

import org.example.dto.store.StoreDto;
import org.example.dto.store.StoreSummaryDto;
import org.example.mapper.BaseMapper;
import org.example.model.Store;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.CDI)
public interface StoreSummaryMapper extends BaseMapper<StoreSummaryDto, Store> {
    StoreSummaryMapper INSTANCE = Mappers.getMapper(StoreSummaryMapper.class);

    @Mapping(target = "managerStaff.firstName",source = "managerFirstName")
    @Mapping(target = "managerStaff.lastName",source = "managerLastName")
    @Mapping(target = "managerStaff.id",source = "managerId")
    @Mapping(target = "id",source = "id")
    @Mapping(target = "address.city.city",source = "city")
    @Mapping(target = "address.address",source = "address")
    @Mapping(target = "address.postalCode",source = "postalCode")
    @Mapping(target = "address.phone",source = "phone")
    Store toEntity(StoreSummaryDto storeSummaryDto);


    @Mapping(source = "managerStaff.firstName",target = "managerFirstName")
    @Mapping(source = "managerStaff.lastName",target = "managerLastName")
    @Mapping(source = "managerStaff.id",target = "managerId")
    @Mapping(source = "id",target = "id")
    @Mapping(source = "address.city.city",target = "city")
    @Mapping(source = "address.address",target = "address")
    @Mapping(source = "address.postalCode",target = "postalCode")
    @Mapping(source = "address.phone",target = "phone")
    StoreSummaryDto toDto(Store store);

    @Named("partialUpdate")
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "managerStaff.firstName",source = "managerFirstName")
    @Mapping(target = "managerStaff.lastName",source = "managerLastName")
    @Mapping(target = "managerStaff.id",source = "managerId")
    @Mapping(target = "id",source = "id")
    @Mapping(target = "address.city.city",source = "city")
    @Mapping(target = "address.address",source = "address")
    @Mapping(target = "address.postalCode",source = "postalCode")
    @Mapping(target = "address.phone",source = "phone")
    void partialUpdate(@MappingTarget Store entity, StoreSummaryDto dto);
}