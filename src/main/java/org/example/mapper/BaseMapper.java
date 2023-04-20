package org.example.mapper;

import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

import javax.enterprise.context.ApplicationScoped;
import java.util.List;
public interface BaseMapper<D, E> {

    E toEntity(D dto);

    D toDto(E entity);

    List<E> toEntity(List<D> dtoList);

    List<D> toDto(List<E> entityList);
    @Named("partialUpdate")
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void partialUpdate(@MappingTarget E entity,D dto);
}
