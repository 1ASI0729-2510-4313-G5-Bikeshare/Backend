package com.bikeshare.backend.bikeInventory.infrastructure.persistence.jpa;

import com.bikeshare.backend.bikeInventory.domain.model.aggregate.BikeTypes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BikeTypesRepository extends JpaRepository<BikeTypes, Long> {

    Optional<BikeTypes> findByTypeName(String typeName);

    boolean existsByTypeName(String typeName);
}
