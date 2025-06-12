package com.bikeshare.backend.rentalOperations.infrastructure.persistence.jpa;

import com.bikeshare.backend.rentalOperations.domain.model.aggregate.Rentals;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RentalsRepository extends JpaRepository<Rentals, Long> {

    Optional<Rentals> findByBikeId_BikeIdAndClientId_UserIdAndStartTime(Long bikeId, Long clientId, String startTime);

    boolean existsByBikeId_BikeIdAndClientId_UserIdAndStartTime(Long bikeId, Long clientId, String startTime);
}
