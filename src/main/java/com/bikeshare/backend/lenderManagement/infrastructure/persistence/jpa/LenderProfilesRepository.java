package com.bikeshare.backend.lenderManagement.infrastructure.persistence.jpa;

import com.bikeshare.backend.lenderManagement.domain.model.aggregate.LenderProfiles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LenderProfilesRepository extends JpaRepository<LenderProfiles, Integer> {
}
