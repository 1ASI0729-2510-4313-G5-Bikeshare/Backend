package com.bikeshare.backend.paymentBilling.infrastructure.persistence.jpa;

import com.bikeshare.backend.paymentBilling.domain.model.aggregate.PaymentStatus;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentStatusRepository extends JpaRepository<PaymentStatus, Long> {
}
