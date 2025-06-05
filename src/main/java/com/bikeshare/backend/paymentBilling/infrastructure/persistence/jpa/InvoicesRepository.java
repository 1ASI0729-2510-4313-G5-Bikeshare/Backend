package com.bikeshare.backend.paymentBilling.infrastructure.persistence.jpa;

import com.bikeshare.backend.paymentBilling.domain.model.aggregate.Invoices;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InvoicesRepository extends JpaRepository<Invoices, Long> {
}
