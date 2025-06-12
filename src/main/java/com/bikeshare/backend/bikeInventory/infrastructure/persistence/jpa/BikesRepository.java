package com.bikeshare.backend.bikeInventory.infrastructure.persistence.jpa;

import com.bikeshare.backend.bikeInventory.domain.model.aggregate.Bikes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BikesRepository extends JpaRepository<Bikes, Long> {

    Optional<Bikes> findByOwnerId_UserId(Long ownerId);

}
