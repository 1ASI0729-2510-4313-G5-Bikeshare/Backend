package com.bikeshare.backend.userManagement.infrastructure.persistance.jpa;

import com.bikeshare.backend.userManagement.domain.model.aggregate.UserRoles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRolesRepository extends JpaRepository<UserRoles, Long> {

    boolean existsByRoleName(String roleName);
}
