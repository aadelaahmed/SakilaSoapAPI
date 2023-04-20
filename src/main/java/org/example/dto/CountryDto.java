package org.example.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.Instant;

/**
 * A DTO for the {@link org.example.model.Country} entity
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CountryDto implements Serializable {
    private Integer id;
    @Size(max = 50)
    @NotNull
    private String country;
    @NotNull
    private Instant lastUpdate;
}