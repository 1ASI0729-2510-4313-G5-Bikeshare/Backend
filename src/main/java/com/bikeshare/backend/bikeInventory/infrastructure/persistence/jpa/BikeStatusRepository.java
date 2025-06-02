package com.bikeshare.backend.bikeInventory.infrastructure.persistence.jpa;

import com.bikeshare.backend.bikeInventory.domain.model.aggregate.BikeStatus;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BikeStatusRepository extends JpaRepository<BikeStatus, Long> {
}
