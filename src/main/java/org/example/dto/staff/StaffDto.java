package org.example.dto.staff;

import org.example.dto.store.StoreDto;
import org.example.model.AddressDto;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.Instant;

/**
 * A DTO for the {@link org.example.model.Staff} entity
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class StaffDto implements Serializable {
    private Integer id;
    @Size(max = 45)
    @NotNull
    private String firstName;
    @Size(max = 45)
    @NotNull
    private String lastName;
    @NotNull
    private AddressDto address;
    @Size(max = 50)
    private String email;
    @NotNull
    private Boolean active = false;
    @Size(max = 16)
    @NotNull
    private String username;
    @Size(max = 40)
    private String password;
    @NotNull
    private Integer storeId;
    @NotNull
    private Instant lastUpdate;
}