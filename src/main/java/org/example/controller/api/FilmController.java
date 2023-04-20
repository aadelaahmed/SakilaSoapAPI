package org.example.controller.api;

import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import jakarta.jws.WebResult;
import jakarta.jws.WebService;
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
import org.example.service.film.FilmServiceImpl;

import java.util.List;
import java.util.Optional;


@WebService(name = "FilmService")
public class FilmController {
    private final FilmServiceImpl service = new FilmServiceImpl(
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
            Optional<FilmDto> optionalFilmDto = Optional.ofNullable(service.create(filmDto, filmDto.getId()));
            if (optionalFilmDto.isPresent()) {
                return optionalFilmDto.get();
            }
            throw new WebServiceException("Can't create this film");
        }
    }

    @WebMethod(operationName = "updateFilm")
    @WebResult(name = "filmUpdated")
    public ResponseMessage updateFilm(@WebParam(name = "id") Integer id, @WebParam(name = "film") FilmDto filmDto) {
        filmDto.setId(id);
        try {
            boolean res = service.update(id, filmDto);
            if (res) {
                return new ResponseMessage("Film was updated successfully");
            } else {
                throw new WebServiceException("Failed to update film");
            }
        } catch (Exception e) {
            throw new WebServiceException("Failed to update film: " + e.getMessage(), e);
        }

    }

    @WebMethod(operationName = "deleteFilm")
    @WebResult(name = "filmDeleted")
    public ResponseMessage deleteFilm(@WebParam(name = "id") Integer id) {
        try {
            boolean res = service.deleteById(id);
            if (res) {
                return new ResponseMessage("Film with ID " + id + " deleted successfully");
            } else {
                throw new WebServiceException("Failed to delete film");
            }
        } catch (Exception e) {
            throw new WebServiceException("Failed to delete film: " + e.getMessage(), e);
        }
    }
}
