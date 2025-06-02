package com.bikeshare.backend.paymentBilling.domain.model.aggregate;

import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Getter
public class PaymentStatus {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long status_id;

    @Column(nullable = false)
    private String status_name;
}
