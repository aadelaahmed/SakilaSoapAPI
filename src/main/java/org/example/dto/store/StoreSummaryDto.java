package org.example.dto.store;

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
    private String managerFirstName;
    private String managerLastName;
    private String address;
    private String city;
    private String phone;
    private String postalCode;
}