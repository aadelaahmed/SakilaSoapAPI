package org.example.mapper.store;

import org.example.dto.store.StoreDto;
import org.example.mapper.BaseMapper;
import org.example.model.Store;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

/*@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING,
        uses = {StaffMapper.class, AddressMapper.class})*/
@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE,
        componentModel = MappingConstants.ComponentModel.CDI)
public interface StoreMapper extends BaseMapper<StoreDto, Store> {
    StoreMapper INSTANCE = Mappers.getMapper(StoreMapper.class);
}