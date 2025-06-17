package com.bikeshare.backend.rentalOperations.infrastructure.persistence.jpa;

import com.bikeshare.backend.rentalOperations.domain.model.aggregate.Rentals;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RentalsRepository extends JpaRepository<Rentals, Long> {

    List<Rentals> findByClientId_Email(String email);

    boolean existsByBikeId_BikeIdAndClientId_UserIdAndStartTime(Long bikeId, Long clientId, String startTime);
}
