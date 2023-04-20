package org.example.mapper.staff;

import org.example.dto.staff.StaffDto;
import org.example.mapper.BaseMapper;
import org.example.model.Staff;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

/*@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE,
        componentModel = MappingConstants.ComponentModel.CDI)*/
@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE,
        componentModel = MappingConstants.ComponentModel.CDI)
public interface StaffMapper extends BaseMapper<StaffDto, Staff> {
    StaffMapper INSTANCE = Mappers.getMapper(StaffMapper.class);

    @Named("partialUpdate")
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "store.id",source = "storeId")
    void partialUpdate(@MappingTarget Staff entity,StaffDto dto);
}