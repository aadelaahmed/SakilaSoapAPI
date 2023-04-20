package org.example.dto.customer;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.Instant;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerSummaryDto implements Serializable {
    private Integer id;
    private String storeAddress;
    @Size(max = 45)
    @NotNull
    private String firstName;
    @Size(max = 45)
    @NotNull
    private String lastName;
    @Size(max = 50)
    private String email;
    private String address;
    private String postalCode;
    private String city;
    @NotNull
    private Boolean active = false;
    @NotNull
    private Instant createDate;
//    private Instant lastUpdate;
}
