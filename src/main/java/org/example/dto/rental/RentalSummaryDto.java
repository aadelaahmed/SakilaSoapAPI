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

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RentalSummaryDto implements Serializable {
    private Integer id;
    private Integer storeId;
    @NotNull
    private Instant rentalDate;
    private String customerEmail;
    private Instant returnDate;
    private String staffUserName;
    @NotNull
    private Instant lastUpdate;
}
