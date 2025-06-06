package com.bikeshare.backend.userManagement.infrastructure.persistance.jpa;

import com.bikeshare.backend.userManagement.domain.model.aggregate.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsersRepository extends JpaRepository<Users, Long> {

    Optional<Users> findByEmail(String email);

    boolean existsByEmail(String email);

    boolean existsByUserId(Long userId);
}
