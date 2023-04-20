package org.example.dto;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.Instant;

/**
 * A DTO for the {@link org.example.model.Film} entity
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class FilmDto implements Serializable {
    private Integer id;
    @Size(max = 128)
    @NotNull
    private String title;
    private String description;
    private Integer releaseYear;
    private Short rentalDuration;
    @NotNull
    private BigDecimal rentalRate;
    private Integer length;
    @Size(max = 5)
    private String rating;
    @NotNull
    private LanguageDto language;
    @NotNull
    private LanguageDto originalLanguage;
    @NotNull
    private Instant lastUpdate;
    @NotNull
    private BigDecimal replacementCost;

    @Override
    public String toString() {
        return "FilmDto{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", releaseYear=" + releaseYear +
                ", rentalDuration=" + rentalDuration +
                ", rentalRate=" + rentalRate +
                ", length=" + length +
                ", rating='" + rating + '\'' +
                ", language=" + language +
                ", originalLanguage=" + originalLanguage +
                ", lastUpdate=" + lastUpdate +
                ", replacementCost=" + replacementCost +
                '}';
    }
}