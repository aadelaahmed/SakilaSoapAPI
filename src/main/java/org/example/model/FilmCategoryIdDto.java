package org.example.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * A DTO for the {@link FilmCategoryId} entity
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class FilmCategoryIdDto implements Serializable {
    private Integer filmId;
    private Short categoryId;
}