package org.example.dto.store;

import org.example.dto.staff.StaffDto;
import org.example.model.AddressDto;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.Instant;

/**
 * A DTO for the {@link org.example.model.Store} entity
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class StoreDto implements Serializable {
    private Integer id;
    @NotNull
    private Integer managerStaffId;
    @NotNull
    private AddressDto address;
    @NotNull
    private Instant lastUpdate;
}