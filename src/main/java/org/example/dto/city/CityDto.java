package org.example.dto.city;

import org.example.dto.CountryDto;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.Instant;

/**
 * A DTO for the {@link org.example.model.City} entity
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CityDto implements Serializable {
    private Integer id;
    @Size(max = 50)
    @NotNull
    private String city;
    @NotNull
    private CountryDto country;
    @NotNull
    private Instant lastUpdate;
}