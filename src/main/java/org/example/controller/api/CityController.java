package org.example.controller.api;

import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import jakarta.jws.WebResult;
import jakarta.jws.WebService;
import jakarta.xml.ws.WebServiceException;
import org.example.dto.city.CityDto;
import org.example.mapper.city.CityMapper;
import org.example.repository.CityRepository;
import org.example.service.CityService;

import java.util.List;
import java.util.Optional;

@WebService(name = "CityService")
public class CityController {

    private final CityService cityService = new CityService(
            new CityRepository(),
            CityMapper.INSTANCE
    );

    @WebMethod(operationName = "getAllCities")
    @WebResult(name = "cities")
    public List<CityDto> getAllCities() {
        return cityService.getAll();
    }

    @WebMethod(operationName = "getCityById")
    @WebResult(name = "city")
    public CityDto getCityById(@WebParam(name = "id") int id) {
        CityDto cityDto = cityService.getCityById(id);
        if (cityDto == null) {
            throw new WebServiceException("City with id " + id + " not found");
        }
        return cityDto;
    }

    @WebMethod(operationName = "createCity")
    @WebResult(name = "cityAdded")
    public CityDto createCity(@WebParam(name = "city") CityDto cityDto) {
        Optional<CityDto> optionalCityDto = Optional.ofNullable(cityService.createByName(cityDto, "city", cityDto.getCity()));
        if (optionalCityDto.isPresent()) {
            return optionalCityDto.get();
        }
        throw new WebServiceException("Can't create city");
    }

    @WebMethod(operationName = "updateCity")
    @WebResult(name = "cityUpdated")
    public String updateCity(@WebParam(name = "id") Integer id, @WebParam(name = "city") CityDto cityDto) {
        cityDto.setId(id);
        boolean res = cityService.update(id, cityDto);
        if (res) {
            return "City was updated successfully";
        } else {
            throw new WebServiceException("Failed to update city");
        }
    }

    @WebMethod(operationName = "deleteCityById")
    @WebResult(name = "cityDeleted")
    public String deleteCityById(@WebParam(name = "id") Integer id) {
        cityService.deleteById(id);
        return "City was deleted successfully";
    }
}