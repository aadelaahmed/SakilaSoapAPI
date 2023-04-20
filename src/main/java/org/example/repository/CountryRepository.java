package org.example.repository;

import org.example.model.Country;

public class CountryRepository extends BaseRepository<Country,Integer> {

    public CountryRepository() {
        super(Country.class);
    }

}
