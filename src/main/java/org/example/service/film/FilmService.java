package org.example.service.film;

import org.example.dto.FilmDto;

import java.util.List;

public interface FilmService {
    List<FilmDto> getAllFilms();
    FilmDto getFilmById(Integer id);
//    List<FilmDto> searchFilms(Integer releaseYear, List<Integer> categoryIds);
    List<FilmDto> getFilmsByActor(Integer actorId);
    List<FilmDto> getAvailableFilms();
    FilmDto saveFilm(FilmDto filmDto);
    List<FilmDto> getFilmsByCategoryId(Integer categoryId);

    FilmDto updateFilm(Integer id,FilmDto filmDto);


    void deleteFilmById(Integer id);
}

