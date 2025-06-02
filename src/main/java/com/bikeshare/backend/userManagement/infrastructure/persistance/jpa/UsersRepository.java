package com.bikeshare.backend.userManagement.infrastructure.persistance.jpa;

import com.bikeshare.backend.userManagement.domain.model.aggregate.Users;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsersRepository extends JpaRepository<Users, Long> {
}
