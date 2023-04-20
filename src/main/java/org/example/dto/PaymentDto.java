package org.example.dto;

import org.example.dto.rental.RentalDto;
import org.example.dto.staff.StaffDto;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.Instant;

/**
 * A DTO for the {@link org.example.model.Payment} entity
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaymentDto implements Serializable {
    private Integer id;
    /*@NotNull
    private CustomerDto customer;*/
    private Integer customerId;
    @NotNull
    private StaffDto staff;
    private RentalDto rental;
    @NotNull
    private BigDecimal amount;
    @NotNull
    private Instant paymentDate;
    private Instant lastUpdate;
}