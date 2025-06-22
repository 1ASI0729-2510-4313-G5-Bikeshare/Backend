package com.bikeshare.backend.bikeInventory.infrastructure.persistence.jpa;

import com.bikeshare.backend.bikeInventory.domain.model.aggregate.Bike;
import com.bikeshare.backend.bikeInventory.domain.model.valueobjects.BikeStatus;
import com.bikeshare.backend.bikeInventory.domain.model.valueobjects.BikeTypes;
import com.bikeshare.backend.bikeInventory.domain.model.valueobjects.OwnerId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BikesRepository extends JpaRepository<Bike, Long> {

    Optional<Bike> findByOwnerId(OwnerId ownerId);

    List<Bike> findByStatus(BikeStatus status);

    boolean existsByModelAndTypeAndIdNot(String model, BikeTypes type, Long id);

    boolean existsByModelAndType(String model, BikeTypes type);
}
