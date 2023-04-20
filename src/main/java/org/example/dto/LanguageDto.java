package org.example.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.Instant;
import java.util.LinkedHashSet;
import java.util.Set;

/**
 * A DTO for the {@link org.example.model.Language} entity
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class LanguageDto implements Serializable {
    private Short id;
    @Size(max = 20)
    @NotNull
    private String name;
    @NotNull
    private Instant lastUpdate;
    /*private Set<FilmDto> films = new LinkedHashSet<>();
    private Set<FilmDto> orgLangFilms = new LinkedHashSet<>();*/
}