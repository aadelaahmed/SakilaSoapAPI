package org.example.service.country;

import org.example.dto.CountryDto;
import org.example.mapper.BaseMapper;
import org.example.mapper.country.CountryMapper;
import org.example.model.Country;
import org.example.repository.BaseRepository;
import org.example.repository.CountryRepository;
import org.example.service.BaseService;

public class CountryService extends BaseService<Country, CountryDto> {
    CountryRepository countryRepository;
    CountryMapper countryMapper;
    public CountryService(CountryRepository countryRepository, CountryMapper countryMapper) {
        super(countryRepository, countryMapper);
        this.countryRepository = countryRepository;
        this.countryMapper = countryMapper;
    }

    @Override
    protected Class<Country> getEntityClass() {
        return Country.class;
    }

    @Override
    protected Class<CountryDto> getDtoClass() {
        return CountryDto.class;
    }
}
