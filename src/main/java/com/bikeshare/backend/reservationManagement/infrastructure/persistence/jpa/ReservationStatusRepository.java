package com.bikeshare.backend.reservationManagement.infrastructure.persistence.jpa;

import com.bikeshare.backend.reservationManagement.domain.model.aggregate.ReservationStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ReservationStatusRepository extends JpaRepository<ReservationStatus, Long> {

    Optional<ReservationStatus> findByStatusName(String statusName);

    boolean existsByStatusName(String statusName);
}
