package org.example.controller.api;

import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import jakarta.jws.WebResult;
import jakarta.jws.WebService;
import jakarta.jws.soap.SOAPBinding;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.GenericEntity;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.xml.ws.WebServiceException;
import org.example.controller.response.ResponseMessage;
import org.example.dto.FilmDto;
import org.example.mapper.ActorMapper;
import org.example.mapper.FilmMapper;
import org.example.repository.ActorRepository;
import org.example.repository.FilmRepository;
import org.example.service.actor.ActorServiceImpl;
import org.example.service.film.FilmService;
import org.example.service.film.FilmServiceImpl;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

@SOAPBinding(style = SOAPBinding.Style.DOCUMENT, parameterStyle = SOAPBinding.ParameterStyle.WRAPPED)

@WebService(name = "FilmService")
public class FilmController {
    private final FilmService service = new FilmService(
            new FilmRepository(), FilmMapper.INSTANCE.INSTANCE
    );

    @WebMethod(operationName = "getFilm")
    @WebResult(name = "film")
    public FilmDto getFilm(@WebParam(name = "id") Integer id) {
        Optional<FilmDto> optionalFilmDto = Optional.ofNullable(service.getById(id));
        return optionalFilmDto.get();
    }

    @WebMethod(operationName = "getAllFilms")
    @WebResult(name = "films")
    public List<FilmDto> getAllFilms() {
        List<FilmDto> filmDtos = service.getAll();
        return filmDtos;
    }

    @WebMethod(operationName = "addFilm")
    @WebResult(name = "filmAdded")
    public FilmDto addFilm(@WebParam(name = "film") FilmDto filmDto) {
        boolean isExisted = service.isExistFilmByTitle(filmDto.getTitle());
        if (isExisted) {
            throw new WebServiceException("The film is already existed");
        } else {
            filmDto.setId(null);
            Optional<FilmDto> optionalFilmDto = Optional.ofNullable(service.createByName(filmDto,"title", filmDto.getTitle()));
            if (optionalFilmDto.isPresent()) {
                return optionalFilmDto.get();
            }
            throw new WebServiceException("Can't create this film");
        }
    }

    @WebMethod(operationName = "updateFilm")
    @WebResult(name = "filmUpdated")
    public FilmDto updateFilm(@WebParam(name = "id") Integer id, @WebParam(name = "film") FilmDto filmDto) {
        filmDto.setId(id);
        filmDto.setLastUpdate(Instant.now());
        FilmDto res = service.update(id, filmDto);
        if (res != null) {
            return res;
        } else
            throw new WebServiceException("Failed to update film");
    }

    @WebMethod(operationName = "deleteFilm")
    @WebResult(name = "filmDeleted")
    public ResponseMessage deleteFilm(@WebParam(name = "id") Integer id) {
        service.deleteById(id);
        return new ResponseMessage("the film was deleted successfully");
    }
}
