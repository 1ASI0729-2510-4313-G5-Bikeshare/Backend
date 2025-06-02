package com.bikeshare.backend.lenderManagement.infrastructure.persistence.jpa;

import com.bikeshare.backend.lenderManagement.domain.model.aggregate.LenderProfiles;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LenderProfilesRepository extends JpaRepository<LenderProfiles, Integer> {
}
