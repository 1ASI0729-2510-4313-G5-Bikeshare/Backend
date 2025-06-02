package com.bikeshare.backend.paymentBilling.infrastructure.persistence.jpa;

import com.bikeshare.backend.paymentBilling.domain.model.aggregate.Payments;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentsRepository extends JpaRepository<Payments, Long> {
}
