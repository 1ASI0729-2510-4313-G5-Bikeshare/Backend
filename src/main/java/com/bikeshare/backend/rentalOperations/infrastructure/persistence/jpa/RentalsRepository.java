package com.bikeshare.backend.rentalOperations.infrastructure.persistence.jpa;

import org.springframework.data.jpa.repository.JpaRepository;

public interface RentalsRepository extends JpaRepository<RentalsRepository, Long> {
}
