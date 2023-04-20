package org.example.service;

import org.example.dto.city.CityDto;
import org.example.mapper.BaseMapper;
import org.example.mapper.city.CityMapper;
import org.example.model.City;
import org.example.repository.BaseRepository;
import org.example.repository.CityRepository;

public class CityService extends BaseService<City, CityDto> {
    CityRepository cityRepository;
    CityMapper cityMapper;
    public CityService(CityRepository cityRepository, CityMapper cityMapper) {
        super(cityRepository, cityMapper);
        this.cityRepository = cityRepository;
        this.cityMapper = cityMapper;
    }

    @Override
    protected Class<City> getEntityClass() {
        return City.class;
    }

    @Override
    protected Class<CityDto> getDtoClass() {
        return CityDto.class;
    }

    public CityDto getCityById(Integer id) {
        return cityRepository.getCityById(id);
    }


}
