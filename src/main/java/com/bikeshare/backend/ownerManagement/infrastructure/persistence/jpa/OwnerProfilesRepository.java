package com.bikeshare.backend.ownerManagement.infrastructure.persistence.jpa;

import com.bikeshare.backend.ownerManagement.domain.model.aggregate.OwnerProfiles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface OwnerProfilesRepository extends JpaRepository<OwnerProfiles, Long> {

    Optional<OwnerProfiles> findByOwner_UserId(Long ownerId);
    boolean existsByOwner_UserId(Long ownerId);

    List<OwnerProfiles> findAllByTotalEarningsGreaterThan(Double totalEarningsAfter);

}