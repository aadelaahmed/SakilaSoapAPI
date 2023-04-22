package org.example.dto.store;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StoreSummaryDto implements Serializable {
    private Short id;
    private int managerId;
    @NotNull
    private String managerFirstName;
    @NotNull
    private String managerLastName;
    @NotNull
    private String address;
    @NotNull
    private String city;
    @NotNull
    private String phone;
    @NotNull
    private String postalCode;
}