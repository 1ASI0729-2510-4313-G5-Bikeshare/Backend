package com.bikeshare.backend.userManagement.infrastructure.persistance.jpa;

import com.bikeshare.backend.userManagement.domain.model.aggregate.UserRoles;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRolesRepository extends JpaRepository<UserRoles, Long> {
}
