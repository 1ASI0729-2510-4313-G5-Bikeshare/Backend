package com.bikeshare.backend.reservationManagement.infrastructure.persistence.jpa;

import com.bikeshare.backend.reservationManagement.domain.model.aggregate.Reservations;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReservationsRepository extends JpaRepository<Reservations, Long> {
    List<Reservations> findByRenter_Email(String renterEmail);
    boolean existsByBikeId_BikeIdAndRenter_UserIdAndStartTime(Long bikeId, Long renterId, String startTime);
}