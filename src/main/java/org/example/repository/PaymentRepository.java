package org.example.repository;

import org.example.model.Payment;

public class PaymentRepository extends BaseRepository<Payment,Integer> {
    public PaymentRepository() {
        super(Payment.class);
    }
}
