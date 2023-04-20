package org.example.dto;

import org.example.model.FilmCategoryIdDto;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.Instant;

/**
 * A DTO for the {@link org.example.model.FilmCategory} entity
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class FilmCategoryDto implements Serializable {
    private FilmCategoryIdDto id;
    private FilmDto film;
    private CategoryDto category;
    @NotNull
    private Instant lastUpdate;
}