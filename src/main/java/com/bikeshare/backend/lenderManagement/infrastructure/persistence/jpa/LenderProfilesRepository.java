package com.bikeshare.backend.lenderManagement.infrastructure.persistence.jpa;

import com.bikeshare.backend.lenderManagement.domain.model.aggregate.LenderProfiles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface LenderProfilesRepository extends JpaRepository<LenderProfiles, Integer> {

    Optional<LenderProfiles> findByLenderId_UserId(Long lenderId);

    List<LenderProfiles> findAllByTotalEarningsGreaterThan(Double totalEarningsAfter);

    boolean existsByLenderId_UserId(Long lenderId);

}
