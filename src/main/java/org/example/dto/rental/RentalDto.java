package org.example.dto.rental;

import org.example.dto.InventoryDto;
import org.example.dto.customer.CustomerDto;
import org.example.dto.staff.StaffDto;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.Instant;

/**
 * A DTO for the {@link org.example.model.Rental} entity
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RentalDto implements Serializable {
    private Integer id;
    @NotNull
    private Instant rentalDate;
    @NotNull
    private InventoryDto inventory;
    @NotNull
    private CustomerDto customer;
    private Instant returnDate;
    @NotNull
    private StaffDto staff;
    @NotNull
    private Instant lastUpdate;
}