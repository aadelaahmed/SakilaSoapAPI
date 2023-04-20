package org.example.mapper;

import org.example.dto.InventoryDto;
import org.example.model.Inventory;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;

/*@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE,
        componentModel = MappingConstants.ComponentModel.CDI, uses = {FilmMapper.class, StoreMapper.class})*/
@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE,
        componentModel = MappingConstants.ComponentModel.CDI)

public interface InventoryMapper extends BaseMapper<InventoryDto, Inventory> {
}