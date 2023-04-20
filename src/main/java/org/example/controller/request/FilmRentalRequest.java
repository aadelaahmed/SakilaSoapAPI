package org.example.controller.request;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.time.Instant;
@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
public class FilmRentalRequest implements Serializable {
   /* @NotNull
    private Instant rentalDate;*/

    @NotNull
    private Integer filmId;

    @NotNull
    private Integer customerId;

    @NotNull
    private Integer staffId;

  /*  @NotNull
    private Instant lastUpdate;*/
}
