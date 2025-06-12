package com.bikeshare.backend.bikeInventory.infrastructure.persistence.jpa;

import com.bikeshare.backend.bikeInventory.domain.model.aggregate.BikeStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BikeStatusRepository extends JpaRepository<BikeStatus, Long> {

    boolean existsByStatusName(String statusName);

}
