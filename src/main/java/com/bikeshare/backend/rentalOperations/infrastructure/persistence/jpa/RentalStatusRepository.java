package com.bikeshare.backend.rentalOperations.infrastructure.persistence.jpa;

import com.bikeshare.backend.rentalOperations.domain.model.aggregate.RentalStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RentalStatusRepository extends JpaRepository<RentalStatus, Long> {

    Optional<RentalStatus> findByStatusName(String statusName);

    boolean existsByStatusName(String statusName);
}
