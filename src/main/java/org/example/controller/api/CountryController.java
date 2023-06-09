package org.example.controller.api;

import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import jakarta.jws.WebResult;
import jakarta.jws.WebService;
import jakarta.jws.soap.SOAPBinding;
import jakarta.ws.rs.core.Response;
import jakarta.xml.ws.WebServiceException;
import org.example.controller.response.ResponseMessage;
import org.example.dto.CountryDto;
import org.example.mapper.country.CountryMapper;
import org.example.repository.CountryRepository;
import org.example.service.country.CountryService;

import java.time.Instant;
import java.util.List;
import java.util.Optional;
@SOAPBinding(style = SOAPBinding.Style.DOCUMENT, parameterStyle = SOAPBinding.ParameterStyle.WRAPPED)

@WebService(name = "CountryService")
public class CountryController {

    private final CountryService countryService = new CountryService(
            new CountryRepository(),
            CountryMapper.INSTANCE
    );

    @WebMethod(operationName = "getAllCountries")
    @WebResult(name = "countries")
    public List<CountryDto> getAllCountries() {
        return countryService.getAll();
    }

    @WebMethod(operationName = "getCountryById")
    @WebResult(name = "country")
    public CountryDto getCountryById(@WebParam(name = "id") int id) {
        CountryDto countryDto = countryService.getById(id);
        if (countryDto == null) {
            throw new WebServiceException("Country with id " + id + " not found");
        }
        return countryDto;
    }

    @WebMethod(operationName = "createCountry")
    @WebResult(name = "countryAdded")
    public CountryDto createCountry(@WebParam(name = "country") CountryDto countryDto) {
        countryDto.setLastUpdate(Instant.now());
        Optional<CountryDto> optionalCountryDto = Optional.ofNullable(countryService.createByName(countryDto,"country", countryDto.getCountry()));
        if (optionalCountryDto.isPresent()) {
            return optionalCountryDto.get();
        }
        throw new WebServiceException("Can't create country");
    }

    @WebMethod(operationName = "updateCountry")
    @WebResult(name = "countryUpdated")
    public CountryDto updateCountry(@WebParam(name = "id") Integer id, @WebParam(name = "country") CountryDto countryDto) {
        countryDto.setId(id);
        countryDto.setLastUpdate(Instant.now());
        CountryDto res = countryService.update(id,countryDto);
        if (res != null) {
            return res;
        }
        throw new WebServiceException("Failed to update country");
    }

    /*@WebMethod(operationName = "deleteCountryById")
    @WebResult(name = "countryDeleted")
    public ResponseMessage deleteCountryById(@WebParam(name = "id") Integer id) {
        countryService.deleteById(id);
        return new ResponseMessage("Country was deleted successfully");
    }*/
}
